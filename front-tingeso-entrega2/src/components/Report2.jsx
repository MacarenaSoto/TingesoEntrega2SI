import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles/R1.css"; // Importa el archivo de estilos CSS

const Report2 = () => {
  const [data, setData] = useState([]);
  const [brands, setBrands] = useState([]); // Agrega un estado para almacenar las marcas [1]

  const fetchData = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8090/api/v1/details/report1vPrueba"
      );
      setData(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const [selectedMonth, setSelectedMonth] = useState("");
  const [additionalMonths, setAdditionalMonths] = useState([]);

  // Manejar el cambio en la selección del mes
  const handleMonthChange = (e) => {
    setSelectedMonth(e.target.value);
  };

  // Lista de meses disponibles
  const months = [
    { value: "01", label: "Enero" },
    { value: "02", label: "Febrero" },
    { value: "03", label: "Marzo" },
    { value: "04", label: "Abril" },
    { value: "05", label: "Mayo" },
    { value: "06", label: "Junio" },
    { value: "07", label: "Julio" },
    { value: "08", label: "Agosto" },
    { value: "09", label: "Septiembre" },
    { value: "10", label: "Octubre" },
    { value: "11", label: "Noviembre" },
    { value: "12", label: "Diciembre" },
  ];

  useEffect(() => {
    // Obtener los índices de los meses anteriores basados en el mes seleccionado
    const selectedMonthIndex = parseInt(selectedMonth) - 1;
    const previousMonth1Index = (selectedMonthIndex - 1 + 12) % 12;
    const previousMonth2Index = (selectedMonthIndex - 2 + 12) % 12;
    const previousMonth3Index = (selectedMonthIndex - 3 + 12) % 12;

    // Crear un array de objetos que contienen los nombres de los meses y su variación
    const additionalMonthsData = [
      { month: months[selectedMonthIndex]?.label, },
      { month: months[previousMonth1Index]?.label, variation: "Variación %" , difference: " (1 mes de diferencia)"},
      { month: months[previousMonth2Index]?.label, variation: "Variación %" , difference: " (2 meses de diferencia)" },
      { month: months[previousMonth3Index]?.label, variation: "Variación %"  , difference: " (3 meses de diferencia)"},
    ];

    setAdditionalMonths(additionalMonthsData);
  }, [selectedMonth]);

  return (
    <div>
      <div>
        <h2>Selecciona un mes:</h2>
        <select value={selectedMonth} onChange={handleMonthChange}>
          <option value="">Selecciona un mes</option>
          {months.map((month) => (
            <option key={month.value} value={month.value}>
              {month.label}
            </option>
          ))}
        </select>
        <p>Mes seleccionado: {selectedMonth}</p>
      </div>
      {selectedMonth && (
      <div className="tabla-container">
        {" "}
        {/* Agrega una clase contenedora para aplicar estilos */}
        <h2>Reporte 2 : Reporte comparativo de reparaciones</h2>
        <table className="tabla-r1">
          {" "}
          {/* Agrega una clase a la tabla para aplicar estilos */}
          <thead>
            <tr>
              <th>Mes</th>
              {additionalMonths.length >= 0 &&
                additionalMonths.map((monthData, index) => (
                  <React.Fragment key={index}>
                    <th>{monthData.month} {monthData.difference}</th>
                    <th>{monthData.variation}</th>
                    
                  </React.Fragment>
                ))}
            </tr>
          </thead>
          <tbody>
            {data.map((item, index) => (
              <tr key={index}>
                <td>{item.patent}</td>
                <td>{item.initialAmount}</td>
                <td>{item.discountByRepair}</td>
                <td>{item.discountByDay}</td>
                <td>{item.discountByBonus}</td>
                <td>{item.surchargeByKm}</td>
                <td>{item.surchargeByDelay}</td>
                <td>{item.surchargeByAge}</td>
                <td>{item.finalAmount}</td>
                <td>{item.date}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
        )}
    </div>
  );
};

export default Report2;
