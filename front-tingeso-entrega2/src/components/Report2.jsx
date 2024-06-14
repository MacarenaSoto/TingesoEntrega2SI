import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles/R1.css"; // Importa el archivo de estilos CSS

const Report2 = () => {
  const [data, setData] = useState([]);
  const [data2, setData2] = useState([]);
  const [brands, setBrands] = useState([]); // Agrega un estado para almacenar las marcas [1]
  const [selectedMonth, setSelectedMonth] = useState("");
  const [selectedYear, setSelectedYear] = useState("2024"); // Año fijo para este ejemplo
  const [additionalMonths, setAdditionalMonths] = useState([]);

  const fetchData = async (month, year) => {
    try {
      const response = await axios.get(
        `http://localhost:6081/api/v2/reports2/report2AllsFiltered?month=${month}&year=${year}`
      );
      setData(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const fetchData2 = async (month, year) => {
    try {
      const response2 = await axios.get(
        `http://localhost:6081/api/v2/reports2/report2Final?month=${month}&year=${year}`
      );
      setData2(response2.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };



  // Manejar el cambio en la selección del mes
  const handleMonthChange = (e) => {
    const selectedMonthValue = e.target.value;
    setSelectedMonth(selectedMonthValue);
    fetchData(selectedMonthValue, selectedYear);
    fetchData2(selectedMonthValue, selectedYear);
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

  const renderRows = () => {
    const rows = [];
    const repairsMap = new Map();
    const repairsMap2 = new Map();

    data.forEach((monthData, monthIndex) => {
      monthData.forEach((repair) => {
        if (!repairsMap.has(repair.repairName)) {
          repairsMap.set(repair.repairName, {});
        }
        repairsMap.get(repair.repairName)[monthIndex] = repair;
      });
    });

    data2.forEach((monthData, monthIndex) => {
      monthData.forEach((repair) => {
        if (!repairsMap2.has(repair.repairName)) {
          repairsMap2.set(repair.repairName, {});
        }
        repairsMap2.get(repair.repairName)[monthIndex] = repair;
      });
    });


    repairsMap.forEach((values, repairName) => {
      rows.push(
        <tr key={repairName}>
          <td rowSpan="2">{repairName}</td>
          {additionalMonths.map((_, index) => (
            <React.Fragment key={index}>
            <td>{values[index]?.nrepairedCars || "-"}</td>
            <td>
              {values[index]?.variation !== undefined 
                ? values[index].variation + "%" 
                : repairsMap2.get(repairName)?.[index]?.npercentageVar !== undefined 
                ? repairsMap2.get(repairName)[index].npercentageVar + "%" 
                : "0%"}
            </td>
            {/*<td></td>  Columna en blanco */}
          </React.Fragment>
          ))}
        </tr>
      );
      
      rows.push(
        <tr key={`${repairName}-amount`}>
          {additionalMonths.map((_, index) => (
            <React.Fragment key={index}>
              <td>{values[index]?.amountRepairedCars || "-"}</td>
              <td>
              {values[index]?.variation !== undefined 
                ? values[index].variation + "%" 
                : repairsMap2.get(repairName)?.[index]?.amountPercentageVar !== undefined 
                ? repairsMap2.get(repairName)[index].amountPercentageVar + "%" 
                : "0.0%"}
            </td>
            </React.Fragment>
          ))}
        </tr>
      );
    });
    console.log(rows);

    return rows;
  };


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
            <thead>
              <tr>
                <th>Reparación</th>
                {additionalMonths.map((monthData, index) => (
                  <React.Fragment key={index}>
                    <th>{monthData.month} {monthData.difference}</th>
                    <th>{monthData.variation}</th>
                  </React.Fragment>
                ))}

              </tr>
            </thead>
            <tbody>{renderRows()} </tbody>
          </table>
      </div>
        )}
    </div>
  );
};

export default Report2;
