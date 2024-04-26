import React from "react";
import "./addManualTicket.css";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
import PrimaryButton from "./../../Buttons/PrimaryButton/PrimaryButton";
const AddManualTicket = () => {
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
                      <PrimaryButton
                        text="Añadir captura de pantalla"
                        backColor="#E1E1E1"
                        fontColor="black"
                      />
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
                        text="Vincular"
                        backColor="#E1E1E1"
                        fontColor="black"
                      />
                    </div>
                  </section>
                </div>
              </div>
              <div className="bottom">
                <PrimaryButton text="Continuar" />
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
};

export default AddManualTicket;
