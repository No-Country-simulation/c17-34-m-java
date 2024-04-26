import React, { useState, useRef } from "react";
import "./addManualTicket.css";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
import PrimaryButton from "./../../Buttons/PrimaryButton/PrimaryButton";
import { CheckIcon } from "../../Icons/Basic/CheckIcon";
import { Link } from "react-router-dom";
const AddManualTicket = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [uploadedImage, setUploadedImage] = useState(null);
  const inputRef = useRef(null);

  const handleButtonClick = () => {
    inputRef.current.click();
  };

  const handleImageUpload = (event) => {
    const file = event.target.files[0];

    if (!file) return;

    setIsLoading(true);

    // Simulando la carga de imagen con un temporizador
    setTimeout(() => {
      const reader = new FileReader();

      reader.onloadend = () => {
        setUploadedImage(reader.result);
        setIsLoading(false);
      };

      reader.readAsDataURL(file);
    }, 3000); // 3 segundos para simular la validación
  };
  return (
    <>
      <TopBarMobile linkTo="/offers/active" title="Agregar entrada" />
      <section>
        <div className="add-manual-ticket-container">
          <div className="add-manual-ticket">
            <div className="content">
              <div className="top">
                <div className="info">
                  <p>
                    En Troca nos comprometemos a promover la seguridad, tu
                    entrada será verificada y validada para ser utilizada en
                    nuestra aplicación y mercado P2P.
                  </p>
                  <p>
                    Por favor, tenga en cuenta que el proceso de validación
                    puede demorar.
                  </p>
                </div>
                <div className="process">
                  <section>
                    <span>1</span>
                    <div className="details">
                      <h2>
                        Seleccione la captura de pantalla que contenga su
                        entrada digital.
                      </h2>
                      <div>
                        <h3>¡ATENCIÓN!</h3>
                        <p>
                          Debe adjuntar UNICAMENTE un recorte de esa captura,
                          donde las cuatro (4) esquinas del QR sean visibles.
                        </p>
                      </div>
                      <input
                        type="file"
                        style={{ display: "none" }}
                        ref={inputRef}
                        onChange={handleImageUpload}
                      />
                      <PrimaryButton
                        type="file"
                        text="Añadir captura de pantalla"
                        backColor="#E1E1E1"
                        fontColor="black"
                        onClick={handleButtonClick}
                      />
                       {isLoading && <div>Validando...</div>}
                      {uploadedImage && !isLoading && (
                        <div className="ticket-validation">
                          <CheckIcon width="30px" height="30px"/>
                          <p className="status">Entrada validada correctamente</p>
                        </div>
                      
                      )}
                    </div>
                  </section>
                  <section>
                    <span>2</span>
                    <div className="details">
                      <h2>Vincula tu cuenta de Mercado Pago.</h2>
                      <p>
                        Vincule su cuenta de Mercado Pago para recibir cobros
                        por su venta de entradas
                      </p>
                      <PrimaryButton
                        text="Vinculada"
                        backColor="var(--color-green)"
                        fontColor="white"
                      />
                    </div>
                  </section>
                </div>
              </div>
              <div className="bottom">
                <Link to="/order/sales">
                  <PrimaryButton text="Continuar" />
                </Link>
                
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
};

export default AddManualTicket;
