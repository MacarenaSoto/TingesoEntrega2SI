import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/R1.css'; // Importa el archivo de estilos CSS

const Report1 = () => {
  const [data, setData] = useState([]);
  const [brands, setBrands] = useState([]); // Agrega un estado para almacenar las marcas [1]
  const [selectedMonth, setSelectedMonth] = useState("");
  const [selectedYear, setSelectedYear] = useState("");
  const [processedData, setProcessedData] = useState({ repairTypes: [], carTypes: [], organizedData: [] });
  const [carTypes, setCarTypes] = useState([]);
  const [carTypesIds, setCarTypesIds] = useState([]);
  const [repairNames, setRepairNames] = useState([]);
  const [summaryData, setSummaryData] = useState([]);
  const [errorMessage, setErrorMessage] = useState(""); // Estado para manejar el mensaje de error

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

  const fetchCarTypes = async () => {
    try {
      const response = await axios.get('http://localhost:6081/api/v2/types/names');
      console.log('Car types:', response.data);
      setCarTypes(response.data);
    } catch (error) {
      console.error('Error fetching car types:', error);
    }
  };

  const fetchRepairNames = async () => {
    try {
      const response = await axios.get('http://localhost:6081/api/v2/repairs/namesNoRepeat');
      console.log('Repair names:', response.data);
      setRepairNames(response.data);
    } catch (error) {
      console.error('Error fetching repair names:', error);
    }
  };

  const fetchCarTypesIds = async () => {
    try {
      const response = await axios.get('http://localhost:6081/api/v2/types/ids');
      console.log('Car types ids:', response.data);
      setCarTypesIds(response.data);
    } catch (error) {
      console.error('Error fetching car types ids:', error);
    }
  }

  const fetchDataPrevious = async () => {
    try{
      const carTypesString = carTypes.join(',');
      const repairNamesString = repairNames.join(',');
      const response = await axios.get (`http://localhost:6081/api/v2/reports1/filterReports?carTypes=${carTypesString}&repairNames=${repairNamesString}`);
      setData(response.data);
      const processed = processData(response.data);
      setProcessedData(processed);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  
  
  const fetchData = async () => {
    try {
      const carTypesString = carTypes.join(',');
      const repairNamesString = repairNames.join(',');
      const response = await axios.get(`http://localhost:6081/api/v2/reports1/filterReportsByDate?carTypes=${carTypesString}&repairNames=${repairNamesString}&month=${selectedMonth}&year=${selectedYear}`);
      if (response.data.length === 0) {
        setErrorMessage("No se encontraron datos para el mes y año seleccionados.");
      }else{
        setErrorMessage("");
      }
      setData(response.data);
      const processed = processData(response.data);
      setProcessedData(processed);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  }; 

  const fetchSummaryData = async () => {
    try {
      const repairNamesString = repairNames.join(',');
      const response = await axios.get(`http://localhost:6081/api/v2/reports1/summarizeRepairs?typeIds=${carTypesIds}&repairNames=${repairNamesString}`);
      console.log('Summary data:', response.data);
      setSummaryData(response.data);
    } catch (error) {
      console.error('Error fetching summary data:', error);
    }
  };
  

  
  

  useEffect(() => {
    fetchCarTypes();
    fetchRepairNames();
    fetchCarTypesIds();
    //fetchSummaryData();
  }, []);

  useEffect(() => {
    if (carTypes.length > 0 && repairNames.length > 0 && !selectedMonth && !selectedYear) {
      fetchDataPrevious();
    }
  }, [carTypes, repairNames, selectedMonth, selectedYear]);

  const processData = (data) => {
    const repairTypes = [...new Set(data.map(item => item.repairName))]; // Tipos de reparación únicos
    const carTypes = [...new Set(data.map(item => item.carType))]; // Tipos de coche únicos

    // Crear un objeto para almacenar los datos organizados
    const organizedData = repairTypes.map(repair => {
      const repairRow = { repairName: repair, nRepairedCars: {}, amountRepairedCars: {}, totalRepairedCars: 0, totalAmountRepairedCars: 0 };

      carTypes.forEach(car => {
        const matchingItem = data.find(item => item.repairName === repair && item.carType === car);
        const nRepairedCars = matchingItem ? matchingItem.nrepairedCars : 0;
        const amountRepairedCars = matchingItem ? matchingItem.amountRepairedCars : 0;
        repairRow.nRepairedCars[car] = nRepairedCars;
        repairRow.amountRepairedCars[car] = amountRepairedCars;
        repairRow.totalRepairedCars += nRepairedCars;
        repairRow.totalAmountRepairedCars += amountRepairedCars;
      });

      return repairRow;
    });

    return { repairTypes, carTypes, organizedData };
  };

  const handleSubmit = () => {
    if (selectedMonth && selectedYear) {
      fetchData();
    } else {
      console.log("Por favor seleccione un mes y un año.");
    }
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
      <div>
      <h2>Ingresa un año:</h2>
        <input
          type="text"
          value={selectedYear}
          onChange={handleYearChange}
          placeholder="Escribe un año (ej. 2022)"
        />
      </div>

      <button onClick={handleSubmit}>Buscar</button>
      {errorMessage && <p>{errorMessage}</p>}
    <div className="tabla-container"> {/* Agrega una clase contenedora para aplicar estilos */}
      <h2>Reporte 1: Listado de reparaciones</h2>
      <table className="tabla-r1"> {/* Agrega una clase a la tabla para aplicar estilos */}
        <thead>
          <tr>
            <th>Lista de Reparaciones</th>
            {processedData.carTypes.map((carType, index) => (
                <th key={index}>{carType}</th>
              ))}
            <th>Total </th>

          </tr>
        </thead>
        <tbody>
            {processedData.organizedData.map((row, index) => (
              <>
                <tr key={index}>
                  <td>{row.repairName}</td>
                  {processedData.carTypes.map((carType, idx) => (
                    <td key={idx}>{row.nRepairedCars[carType]}</td>
                  ))}
                  <td>{row.totalRepairedCars}</td>
                </tr>
                <tr key={`${index}-amount`}>
                  <td></td>
                  {processedData.carTypes.map((carType, idx) => (
                    <td key={idx}>{row.amountRepairedCars[carType] === 0 ? '-' : row.amountRepairedCars[carType]}</td>
                  ))}
                  <td>{row.totalAmountRepairedCars}</td>
                </tr>
              </>
            ))}
          </tbody>
      </table>
    </div>
    </div>
  );
};

export default Report1;
