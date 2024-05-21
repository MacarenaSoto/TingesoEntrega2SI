import './App.css'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Navbar from "./components/Navbar"
import Home from './components/Home';
import NotFound from './components/NotFound';
import Prueba from './components/Prueba';
import AddCar from './components/AddCar';
import AddRepair from './components/AddRepair';
import NewDetail from './components/NewDetail';
import Detail from './components/Detail';
import Prueba2 from './components/Prueba2';
import R1 from './components/R1';
import R2 from './components/R2';
import R3 from './components/R3';
import R4 from './components/R4';
import AddBonus from './components/AddBonus';
import AddTypeRepair from './components/AddTypeRepair';
import AddBrand from './components/AddBrand';
import AddType from './components/AddType';
import AddEngine from './components/AddEngine';
import Report1 from './components/Report1';
import Report2 from './components/Report2';


function App() {
  return (
      <Router>
          <div className="container">
          <Navbar></Navbar>
            <Routes>
              <Route path="/home" element={<Home/>} />
              <Route path="/prueba" element={<Prueba/>} />
              <Route path="/prueba2" element={<Prueba2/>} />
              <Route path="*" element={<NotFound/>} />
              <Route path="/" element={<Home/>} />
              <Route path="/AddCar" element={<AddCar/>} />
              <Route path="/AddRepair" element={<AddRepair/>} />
              <Route path="/NewDetail" element={<NewDetail/>} />
              <Route path="/Detail" element={<Detail/>} />
              <Route path="/R1" element={<R1/>} />
              <Route path="/R2" element={<R2/>} />
              <Route path="/R3" element={<R3/>} />
              <Route path="/R4" element={<R4/>} />
              <Route path="/addBonus" element={<AddBonus/>} />
              <Route path="/AddTypeRepair" element={<AddTypeRepair/>} />
              <Route path="/AddBrand" element={<AddBrand/>} />
              <Route path="/AddType" element={<AddType/>} />
              <Route path="/AddEngine" element={<AddEngine/>} />
              <Route path="/Report1" element={<Report1/>} />
              <Route path="/Report2" element={<Report2/>} />

              
            </Routes>
          </div>
      </Router>
  );
}

export default App
