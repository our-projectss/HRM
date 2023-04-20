import React from 'react';
import * as faceapi from 'face-api.js';
import { Box, CircularProgress, Modal } from '@mui/material';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

export default function Checkin() {
  const [modelsLoaded, setModelsLoaded] = React.useState(false);
  const [captureVideo, setCaptureVideo] = React.useState(false);
  const [loading, setLoading] = React.useState(false);
  const [src, setSrc] = React.useState(false);

  const videoRef = React.useRef();
  const videoHeight = 480;
  const videoWidth = 640;
  const canvasRef = React.useRef();
  const canvas2Ref = React.useRef();

  React.useEffect(() => {
    const loadModels = async () => {
      const MODEL_URL = process.env.PUBLIC_URL + "/models";

      Promise.all([
        faceapi.nets.tinyFaceDetector.loadFromUri(MODEL_URL),
        faceapi.nets.faceLandmark68Net.loadFromUri(MODEL_URL),
        faceapi.nets.faceRecognitionNet.loadFromUri(MODEL_URL),
        faceapi.nets.faceExpressionNet.loadFromUri(MODEL_URL),
      ]).then(setModelsLoaded(true));
    }
    loadModels();
  }, []);

  const startVideo = () => {
    setCaptureVideo(true);
    navigator.mediaDevices
      .getUserMedia({ video: { width: 300 } })
      .then(stream => {
        let video = videoRef.current;
        video.srcObject = stream;
        video.play();
      })
      .catch(err => {
        console.error("error:", err);
      });
  }

  const handleVideoOnPlay = () => {
    let count = 0;
    setInterval(async () => {
      if (canvasRef && canvasRef.current) {
        canvasRef.current.innerHTML = faceapi.createCanvasFromMedia(videoRef.current);
        const displaySize = {
          width: videoWidth,
          height: videoHeight
        }

        faceapi.matchDimensions(canvasRef.current, displaySize);

        const detections = await faceapi.detectAllFaces(videoRef.current, new faceapi.TinyFaceDetectorOptions()).withFaceLandmarks().withFaceExpressions();

        const resizedDetections = faceapi.resizeResults(detections, displaySize);
        if (resizedDetections.length && resizedDetections[0].detection._score > 0.5) {
          ++ count;

          if (count >= 8) {
            setLoading(true);
            count = 0;
          }
        } else {
          count = 0;
        }

        if (count < 8) {
          canvasRef && canvasRef.current && canvasRef.current.getContext('2d').clearRect(0, 0, videoWidth, videoHeight);
          canvasRef && canvasRef.current && faceapi.draw.drawDetections(canvasRef.current, resizedDetections);
          // canvasRef && canvasRef.current && faceapi.draw.drawFaceLandmarks(canvasRef.current, resizedDetections);
          // canvasRef && canvasRef.current && faceapi.draw.drawFaceExpressions(canvasRef.current, resizedDetections);
        }
      }
    }, 500)
  }

  const capture = () => {
    const canvas = canvas2Ref.current;
    console.log(canvas)
    const video = videoRef.current;
    canvas.width = video?.videoWidth;
    canvas.height = video?.videoHeight;
    canvas
      .getContext("2d")
      .drawImage(video, 0, 0, video?.videoWidth, video?.videoHeight);
  
    const playImage = new Image();
    playImage.src = "path to image asset";
    playImage.onload = () => {
      const startX = video?.videoWidth / 2 - playImage?.width / 2;
      const startY = video?.videoHeight / 2 - playImage?.height / 2;
      canvas
        .getContext("2d")
        .drawImage(playImage, startX, startY, playImage?.width, playImage?.height);
      canvas.toBlob((blob) => {
        const img = new Image();
        img.src = window.URL.createObjectUrl(blob);
      });
    };
  }

  React.useEffect(() => {
    if (loading) {
      capture()

      const timeoutID = setTimeout(() => {
        setLoading(false);
      }, 6000);
      return () => {
        clearTimeout(timeoutID);
      };
    }
  }, [loading])

  const closeWebcam = () => {
    videoRef.current.pause();
    videoRef.current.srcObject.getTracks()[0].stop();
    setCaptureVideo(false);
  }

  return (
    <div>
      <div style={{ textAlign: 'center', padding: '10px' }}>
        {
          captureVideo && modelsLoaded ?
            <button onClick={closeWebcam} style={{ cursor: 'pointer', backgroundColor: 'green', color: 'white', padding: '15px', fontSize: '25px', border: 'none', borderRadius: '10px' }}>
              Close Webcam
            </button>
            :
            <button onClick={startVideo} style={{ cursor: 'pointer', backgroundColor: 'green', color: 'white', padding: '15px', fontSize: '25px', border: 'none', borderRadius: '10px' }}>
              Open Webcam
            </button>
        }
      </div>
      {
        captureVideo ?
          modelsLoaded ?
            <div>
              <div style={{ display: 'flex', justifyContent: 'center', padding: '10px' }}>
                <video ref={videoRef} height={videoHeight} width={videoWidth} onPlay={handleVideoOnPlay} style={{ borderRadius: '10px' }} />
                <canvas ref={canvasRef} style={{ position: 'absolute' }} />
              </div>
            </div>
            :
            <div>loading...</div>
          :
          <>
          </>
      }
      <Modal
        open={true}
        sx={loading ? {} : { display: 'none' }}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <CircularProgress />
          <canvas ref={canvas2Ref} style={{overflow: 'auto'}}></canvas>
        </Box>
      </Modal>
    </div>
  )
}
