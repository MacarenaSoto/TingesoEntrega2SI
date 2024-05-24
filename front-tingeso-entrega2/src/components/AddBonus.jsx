import React, { useEffect, useState } from "react";
import axios from "axios";

import MoreTimeIcon from "@mui/icons-material/MoreTime";
import "../styles/AddCar.css";

const AddCar = () => {
  const [brandId, setBrandId] = useState("");
  const [number, setNumber] = useState("");
  const [amount, setAmount] = useState("");
  const [brandOptions, setBrand] = useState([]);
  const [brandName, setBrandName] = useState("");


  useEffect(() => {
    const fetchBrands = async () => {
      try {
        const response = await axios.get("http://localhost:6081/api/v2/brands/all");
        setBrand(response.data);  // Asumiendo que `response.data` es el array de marcas
        console.log("Brands response data:", response.data);
      } catch (error) {
        console.error("Error fetching brands:", error);
      }
    };

    fetchBrands();
  }, []);




  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:6081/api/v2/bonus/add",
        {
          brandId: brandId,
          number: number,
          amount: amount,
          brand: brandName,
        }
      );

      console.log("Respuesta del backend:", response.data);

      // Aquí podrías mostrar un mensaje de éxito o redirigir a otra página
    } catch (error) {
      console.error("Error al enviar los datos:", error);
      // Aquí podrías mostrar un mensaje de error al usuario
    }
  };

  return (
    <div className="add-car-container">
      <h1 className="add-car-title">Agregar un Nuevo Bonus</h1>
      <div className="add-car-card">
        <form onSubmit={handleSubmit} className="add-car-form">
          <div className="form">
            <label>
              Marca:
              <select
                value={brandId}
                onChange={(e) => {
                  const selectedBrandValue = e.target.value;
                  setBrandId(selectedBrandValue);
                  console.log("selectedBrandValue", selectedBrandValue);
                
                  // Convertir a número si los ID son numéricos
                  const selectedBrandId = Number(selectedBrandValue);
                  console.log("selectedBrandId", selectedBrandId);
                
                  // Asegurarse de comparar correctamente según el tipo de dato de brand.id
                  const selectedBrand = brandOptions.find((brand) => brand.id === selectedBrandId);
                  console.log("selectedBrandName", selectedBrand);
                
                  if (selectedBrand) {
                    setBrandName(selectedBrand.name);
                  } else {
                    setBrandName("yyyy"); // Configura esto para manejar marcas no encontradas
                  }
                }}
                


              >
                <option value="">Selecciona una marca</option>
                {brandOptions.map((brand) => (
                  <option key={brand.id} value={brand.id}>
                    {brand.name}
                  </option>
                ))}
              </select>
            </label>
            <label>
              Cantidad de bonos:
              <input
                type="text"
                value={number}
                onChange={(e) => setNumber(e.target.value)}
              />
            </label>
            <label>
              Monto del bono:
              <input
                type="text"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
              />
            </label>

            {/* <FormControl fullWidth>
              <TextField
                id="month"
                label="PRUEBA"
                value={{ engineId }}
                select
                variant="standard"
                defaultValue="1"
                onChange={(e) => setMonth(e.target.value)}
                style={{ width: "25%" }}
              >
                <MenuItem value={1}>Enero</MenuItem>
                <MenuItem value={2}>Febrero</MenuItem>
                <MenuItem value={3}>Marzo</MenuItem>
                <MenuItem value={4}>Abril</MenuItem>
                <MenuItem value={5}>Mayo</MenuItem>
                <MenuItem value={6}>Junio</MenuItem>
                <MenuItem value={7}>Julio</MenuItem>
                <MenuItem value={8}>Agosto</MenuItem>
                <MenuItem value={9}>Septiembre</MenuItem>
                <MenuItem value={10}>Octubre</MenuItem>
                <MenuItem value={11}>Noviembre</MenuItem>
                <MenuItem value={12}>Diciembre</MenuItem>
              </TextField>
            </FormControl> */}
          </div>
          <button startIcon={<MoreTimeIcon />} type="submit">
            Agregar Bono
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddCar;
