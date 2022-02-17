const storageUtils = {
  set: (key, value) => {
    if (typeof (value) === 'object') {
      value = JSON.stringify(value)
    }
    localStorage.setItem(key, value)
  },
  get: (key, format) => {
    let value = localStorage.getItem(key)
    if (!value) return null
    return format ? JSON.parse(value) : value
  },
  remove: (key) => {
    localStorage.removeItem(key)
  }
}

export { storageUtils }