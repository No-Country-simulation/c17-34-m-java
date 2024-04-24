import React from "react";

const PrimaryButton = ({
  text,
  onClick,
  type,
  backColor,
  fontColor,
}) => {
  const buttonStyles = {
    width: "100%",
    height: "auto",
    cursor: "pointer",
    padding: "3.846vw 0 3.846vw 0",
    backgroundColor: backColor || "black",
    color: fontColor || "white",
    border: "none",
    borderRadius: "5.128vw",
    fontSize: "4.615vw",
    textAlign: 'center'
  };
  return (
    <button style={buttonStyles} onClick={onClick} type={type}>
      {text}
    </button>
  );
};

export default PrimaryButton;
