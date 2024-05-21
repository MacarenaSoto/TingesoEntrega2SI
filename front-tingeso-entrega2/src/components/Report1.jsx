import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/R1.css'; // Importa el archivo de estilos CSS

const Report1 = () => {
  const [data, setData] = useState([]);
  const [brands, setBrands] = useState([]); // Agrega un estado para almacenar las marcas [1]
  const [selectedMonth, setSelectedMonth] = useState("");
  const [selectedYear, setSelectedYear] = useState("");

  // Manejar el cambio en la selección del mes
  const handleMonthChange = (e) => {
    setSelectedMonth(e.target.value);
  };

    // Manejar el cambio en la selección del año
    const handleYearChange = (e) => {
      setSelectedYear(e.target.value);
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
  
  const fetchData = async () => {
    try {
      const response = await axios.get('http://localhost:8090/api/v1/details/report1vPrueba');
      setData(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    axios.get('http://localhost:8081/api/v2/brands/all') // Realiza una petición para obtener las marcas [2]
        .then((response) => {
            setBrands(response.data);
            })
    fetchData();
  }, []);

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
      <div>
      <h2>Ingresa un año:</h2>
        <input
          type="text"
          value={selectedYear}
          onChange={handleYearChange}
          placeholder="Escribe un año (ej. 2022)"
        />
      </div>
    <div className="tabla-container"> {/* Agrega una clase contenedora para aplicar estilos */}
      <h2>Reporte 1: Listado de reparaciones</h2>
      <table className="tabla-r1"> {/* Agrega una clase a la tabla para aplicar estilos */}
        <thead>
          <tr>
            <th>Lista de Reparaciones</th>
            {brands.map((brands, index) => (
            <th key={index}>{brands.name}</th>
          ))}
            <th>Total </th>

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
    </div>
  );
};

export default Report1;
