import React from "react";
import "./primaryButton.css"
const PrimaryButton = ({
  text,
  onClick,
  type,
  backColor,
  fontColor,
  disabled
}) => {
  const buttonStyles = {
    backgroundColor: backColor || "black",
    color: fontColor || "white",
  };
  return (
    <button style={buttonStyles} className="primary-button" disabled={disabled} onClick={onClick} type={type}>
      {text}
    </button>
  );
};

export default PrimaryButton;
