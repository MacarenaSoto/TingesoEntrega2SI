import * as React from "react";
import Box from "@mui/material/Box";
import Drawer from "@mui/material/Drawer";
import List from "@mui/material/List";
import Divider from "@mui/material/Divider";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import PeopleAltIcon from "@mui/icons-material/PeopleAlt";
import PaidIcon from "@mui/icons-material/Paid";
import CalculateIcon from "@mui/icons-material/Calculate";
import AnalyticsIcon from "@mui/icons-material/Analytics";
import DiscountIcon from "@mui/icons-material/Discount";
import HailIcon from "@mui/icons-material/Hail";
import MedicationLiquidIcon from "@mui/icons-material/MedicationLiquid";
import MoreTimeIcon from "@mui/icons-material/MoreTime";
import AddIcon from "@mui/icons-material/Add";
import HomeIcon from "@mui/icons-material/Home";
import { useNavigate } from "react-router-dom";
import LeaderboardOutlinedIcon from "@mui/icons-material/LeaderboardOutlined";
import BuildIcon from "@mui/icons-material/Build";
import IconButton from "@mui/material/IconButton";
import AccessTimeFilledIcon from "@mui/icons-material/AccessTimeFilled";

export default function Sidemenu({ open, toggleDrawer }) {
  const navigate = useNavigate();

  const listOptions = () => (
    <Box role="presentation" onClick={toggleDrawer(false)}>
      <List>
        <ListItemButton onClick={() => navigate("/home")}>
          <ListItemIcon>
            <HomeIcon />
          </ListItemIcon>
          <ListItemText primary="Home" />
        </ListItemButton>

        <Divider />

        <ListItemButton onClick={() => navigate("/addBonus")}>
          <ListItemIcon>
            <AddIcon />
            {/* <CalculateIcon /> */}
          </ListItemIcon>
          <ListItemText primary="Agregar bono" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/r1")}>
          <ListItemIcon>
            <AnalyticsIcon /> {/* <CalculateIcon /> */}
          </ListItemIcon>
          <IconButton>
            <BuildIcon />
          </IconButton>
          <ListItemText primary="       R1:  Cálculos de Reparaciones" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/r2")}>
          <ListItemIcon>
            <AnalyticsIcon /> {/* <CalculateIcon /> */}
          </ListItemIcon>
          <ListItemIcon>
            <LeaderboardOutlinedIcon />
          </ListItemIcon>
          <ListItemText primary="R2: LISTADO 11 REPARACIONES VS TIPOS DE VEHÍCULOS" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/r3")}>
          <ListItemIcon>
            <AnalyticsIcon />
          </ListItemIcon>
          <ListItemIcon>
            <AccessTimeFilledIcon />
          </ListItemIcon>
          <ListItemText primary="R3: LISTADO TIEMPOS PROMEDIO POR MARCA" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/r4")}>
          <ListItemIcon>
            <AnalyticsIcon /> {/* <CalculateIcon /> */}
          </ListItemIcon>
          <ListItemIcon>
            <LeaderboardOutlinedIcon />
          </ListItemIcon>
          <ListItemText primary="R4: LISTADO 11 REPARACIONES VS TIPO MOTOR" />
        </ListItemButton>
      </List>

      <Divider />

      {/* <List>
        <ListItemButton onClick={() => navigate("/employee/discounts")}>
          <ListItemIcon>
            <DiscountIcon />
          </ListItemIcon>
          <ListItemText primary="Descuentos" />
        </ListItemButton>
        <ListItemButton onClick={() => navigate("/paycheck/vacations")}>
          <ListItemIcon>
            <HailIcon />
          </ListItemIcon>
          <ListItemText primary="Vacaciones" />
        </ListItemButton>
        <ListItemButton onClick={() => navigate("/paycheck/medicalleave")}>
          <ListItemIcon>
            <MedicationLiquidIcon />
          </ListItemIcon>
          <ListItemText primary="Licencias Medicas" />
        </ListItemButton>
      </List> */}
    </Box>
  );

  return (
    <div>
      <Drawer anchor={"left"} open={open} onClose={toggleDrawer(false)}>
        {listOptions()}
      </Drawer>
    </div>
  );
}
