
export const getAccessToken = () => {
  return localStorage.getItem("accessToken");
}

export const removeAccessToken = () => {
  return localStorage.removeItem('accessToken')
}

export const setAccessToken = (newToken) => {
  return localStorage.setItem('accessToken', newToken);
}
