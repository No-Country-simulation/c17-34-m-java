import React from "react";
import "./secondaryButton.css"
const SecondaryButton = ({
  text,
  type,
  backColor,
  fontColor,
  onClick
}) => {
  const buttonStyles = {
    backgroundColor: backColor || "black",
    color: fontColor || "white",
  };
  return (
    <button className="secondary-button" style={buttonStyles} onClick={onClick} type={type || 'button'}>
      {text}
    </button>
  );
};

export default SecondaryButton;
