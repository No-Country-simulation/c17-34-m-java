import React from "react";
import "./secondaryButton.css"
const SecondaryButton = ({
  text,
  onClick,
  type,
  backColor,
  fontColor,
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
