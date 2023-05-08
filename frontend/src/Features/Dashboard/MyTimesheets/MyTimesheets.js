import MoreVertIcon from '@mui/icons-material/MoreVert';
import { Backdrop, Button, CircularProgress, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, MenuItem, Select, TextField } from '@mui/material';
import moment from 'moment';
import React, { useEffect, useState } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import "react-big-calendar/lib/css/react-big-calendar.css";
import axiosInstance from '../../../Api';
import { getUser } from '../../../utils/localStorage';
import "../MyRequestOff/MyRequestOff.css";

const localizer = momentLocalizer(moment)

const user = getUser()
const defaultDailyForm = {
  day: "",
  detail: "",
  hours: "8",
  userId: user?.id,
} 

export default function MyTimesheets() {
  const [open, setOpen] = useState(false);
  const [openForm, setOpenForm] = useState(false);
  const [data, setData] = useState([]);
  const [daily, setDaily] = useState([])

  const [dailyForm, setDailyForm] = useState(defaultDailyForm);


  useEffect(() => {
    async function handle() {
      try {
        const res = await axiosInstance.get("/api/daily", {
          params: {
            userId: user?.id
          }
        });

        if (res) { 
          setData(res.data)
          const temp = res.data.map(element => {
            return {
              start: moment(element.day).toDate(),
              end: moment(element.day).toDate(),
              title: element.hours + "h: " + element.detail,
            }
          });
          setDaily(temp)
        }
      } catch(error) {
        throw(error)
      }
    }
    handle()
  }, [openForm])

  const getRequestOff = (time) => {
    return data.find(e => moment(e.day).format("YYYY-MM-DD") === moment(time).format("YYYY-MM-DD"))
  }

  const handleClickADay = (e) => {
    const daily = getRequestOff(e?.start)
    if (daily) {
      setDailyForm(daily)
    }

    let newForm = {
      ...dailyForm,
      day: moment(e.start).format("YYYY-MM-DD")
    }

    if (daily) {
      newForm = {
        ...newForm,
        ...daily
      }
    }

    setDailyForm(newForm)
    setOpenForm(true)
  }

  const handleCreateDaily = async () => {
    try {
      await axiosInstance.post("/api/daily", dailyForm);
      setOpenForm(false)
      setDailyForm(defaultDailyForm)
    } catch(error) {
      throw(error)
    }
  }

  return (
    <div id='my-request-off'>
      <div className='header'>
        <h1>My timesheet</h1>
        <MoreVertIcon />
      </div>
      <div className='my-request-off-container'>
        <Calendar
          localizer={localizer}
          events={daily}
          startAccessor="start"
          endAccessor="end"
          views={['month']}
          style={{ height: 500 }}
          selectable
          onSelectSlot={handleClickADay}
        />
      </div>
      <Dialog
        open={openForm}
        onClose={() => setOpenForm(false)}
      >
        <DialogTitle 
          sx={{
            minWidth: '500px'
          }}
        >
          Cerate daily
        </DialogTitle>
        <DialogContent>
          <DialogContentText>You are creating a daily on {dailyForm.day}</DialogContentText>
          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={dailyForm.hours}
            label="Hours"
            onChange={(e) => setDailyForm({
              ...dailyForm,
              hours: e.target.value
            })}
            fullWidth
            sx={{
              marginTop: "16px"
            }}
          >
            <MenuItem value={"8"}>8h</MenuItem>
            <MenuItem value={"4"}>4h</MenuItem>
          </Select>
          <TextField
            margin="dense"
            id="name"
            label="Detail"
            type="text"
            fullWidth
            value={dailyForm.detail}
            onChange={(e) => setDailyForm({
              ...dailyForm,
              detail: e.target.value
            })}
            sx={{
              marginTop: "16px"
            }}
          />
        </DialogContent>
        <DialogActions
          sx={{
            padding: '0 24px 24px 24px'
          }}
        >
          <Button 
            onClick={() => {
              setOpenForm(false)
              setDailyForm(defaultDailyForm)
            }} 
            variant="outlined"
          >
            Cancel
          </Button>
          <Button onClick={handleCreateDaily} variant="contained">Save</Button>
        </DialogActions>
      </Dialog>
      <Backdrop
        sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
        open={open}
        onClick={() => setOpen(false)}
      >
        <CircularProgress color="inherit" />
      </Backdrop>
    </div>
  )
}
