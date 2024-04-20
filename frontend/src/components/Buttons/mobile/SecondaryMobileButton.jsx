import React from "react";

const SecondaryMobileButton = ({
  children,
  onClick,
  type,
  backColor,
  fontColor,
}) => {
  const buttonStyles = {
    width: "24.359vw",
    height: "7.436vw",
    backgroundColor: backColor || "black",
    color: fontColor || "white",
    border: "none",
    borderRadius: "5.128vw",
    fontSize: "3.59vw",
    textAlign: "center",
  };
  return (
    <button style={buttonStyles} onClick={onClick} type={type || 'button'}>
      {children}
    </button>
  );
};

export default SecondaryMobileButton;
