
export const getAccessToken = () => {
  return localStorage.getItem("accessToken");
}

export const removeAccessToken = () => {
  return localStorage.removeItem('accessToken')
}

export const setAccessToken = (newToken) => {
  return localStorage.setItem('accessToken', newToken);
}

export const getUser = () => {
  const user = localStorage.getItem("user")
  return JSON.parse(user);
}

export const removeUser = () => {
  return localStorage.removeItem('user')
}

export const setUser = (newUser) => {
  return localStorage.setItem('user', JSON.stringify(newUser));
}
