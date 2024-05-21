import React, { useState } from 'react';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import TimePicker from 'react-time-picker';
import 'react-time-picker/dist/TimePicker.css';
import '../styles/AddRepair.css';

const isValidHourFormat = (hour) => {
    // Expresión regular para validar el formato de hora (HH:MM)
    const regex = /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;
    return regex.test(hour);
  };
  

const AddRepair = () => {
    const [patent, setPatent] = useState('');
    const [admissionDate, setAdmissionDate] = useState(null);
    const [admissionHour, setAdmissionHour] = useState('');
    const [repairType, setRepairType] = useState('');
    const [exitDate, setExitDate] = useState(null);
    const [exitHour, setExitHour] = useState('');
    const handleAdmissionHourChange = (e) => {
        const { value } = e.target;
        // Verificar si el formato de hora es válido antes de actualizar el estado
        if (isValidHourFormat(value)) {
          setAdmissionHour(value);
        } else {
          // Mostrar un mensaje de error o realizar alguna otra acción en caso de formato inválido
          console.error('Formato de hora inválido');
        }
      };
      
      const handleExitHourChange = (e) => {
        const { value } = e.target;
        // Verificar si el formato de hora es válido antes de actualizar el estado
        if (isValidHourFormat(value)) {
          setExitHour(value);
        } else {
          // Mostrar un mensaje de error o realizar alguna otra acción en caso de formato inválido
          console.error('Formato de hora inválido');
        }
      };
    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            // Formatear la fecha de ingreso
            const formattedAdmissionDate = admissionDate ? admissionDate.toISOString().split('T')[0] : null;
            
            // Formatear la fecha de salida
            const formattedExitDate = exitDate ? exitDate.toISOString().split('T')[0] : null;

            // Formatear la hora de ingreso
            const formattedAdmissionHour = admissionHour ? admissionHour : null;

            // Formatear la hora de salida
            const formattedExitHour = exitHour ? exitHour : null;

            const response = await axios.post('http://localhost:8090/api/v1/repairs/add', {
                patent: patent,
                admissionDate: formattedAdmissionDate,
                admissionHour: formattedAdmissionHour,
                repairType: repairType,
                exitDate: formattedExitDate,
                exitHour: formattedExitHour
            });

            console.log('Respuesta del backend:', response.data);

            // Aquí podrías mostrar un mensaje de éxito o redirigir a otra página
        } catch (error) {
            console.error('Error al enviar los datos:', error);
            // Aquí podrías mostrar un mensaje de error al usuario
        }
    };

    return (
        <div className="add-car-container">
            <h1>Agregar una nueva Reparación</h1>
            <form onSubmit={handleSubmit} className="add-car-form">
                <div className='form'>
                <label>
                    Patente del vehículo:
                    <input type="text" value={patent} onChange={(e) => setPatent(e.target.value)} />
                </label>
                <label>
                    Fecha de ingreso:
                    <DatePicker // Usa el componente DatePicker
                        selected={admissionDate}
                        onChange={(date) => setAdmissionDate(date)}
                    />
                </label>
                <label>
                        Hora de ingreso:
                        <TimePicker
                            value={admissionHour}
                            onChange={setAdmissionHour}
                            style={{
                                color: 'red',
                                fontSize: '24px',
                                marginRight: '8px'
                            }}
                        />
                    </label>
                <label>
                    Tipo de reparación
                    <select value={repairType} onChange={(e) => setRepairType(e.target.value)}>
                        <option value="">Seleccione el tipo de reparación</option>
                        <option value="1">1. Reparaciones del sistema de frenos</option>
                        <option value="2">2. Servicio del sistema de refrigeración</option>
                        <option value="3">3. Reparaciones del motor</option>
                        <option value="4">4. Reparaciones de la Transmisión</option>
                        <option value="5">5. Reparación del sistema eléctrico</option>
                        <option value="6">6. Reparaciones del sistema de escape</option>
                        <option value="7">7. Reparación de neumáticos y ruedas</option>
                        <option value="8">8. Reparaciones de la suspensión y la dirección</option>
                        <option value="9">9. Reparación del sistema de aire acondicionado y calefacción</option>
                        <option value="10">10. Reparaciones del sistema de combustible</option>
                        <option value="11">11. Reparación y reemplazo del parabrisas y ventanas dañadas</option>
                    </select>
                </label>
                <label>
                    Fecha de Salida:
                    <DatePicker // Usa el componente DatePicker
                        selected={exitDate}
                        onChange={(date) => setExitDate(date)}
                    />
                </label>
                <label>
                        Hora de Salida:
                        <TimePicker
                            value={exitHour}
                            onChange={setExitHour}
                            style={{ color: 'blue !important' }}
                        />
                    </label>
                
                </div>
                <button type="submit">Agregar Reparación</button>
            </form>
            
        </div>
    );
};

export default AddRepair;
