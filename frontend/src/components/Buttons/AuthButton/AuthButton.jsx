import React from 'react'
import "./authButton.css"
const AuthButton = ({title}) => {
  return (
    <button className='auth-button'>
      {title}
    </button>
  )
}

export default AuthButton
