package tingeso.reports_service.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tingeso.reports_service.entities.Report2Aux;
import tingeso.reports_service.entities.Report2Entity;

import tingeso.reports_service.request.RequestDetail;
import tingeso.reports_service.request.RequestRepair;

import tingeso.reports_service.clients.DetailsFeignClient;
import tingeso.reports_service.clients.RepairFeignClient;

@Service
public class Report2Service {

    @Autowired
    private DetailsFeignClient detailsFeignClient;

    @Autowired
    private RepairFeignClient repairFeignClient;

    // Trae los detalles del MS car-repair-service
    public ArrayList<RequestDetail> getDetails() {
        // System.out.println(" EN REPORT 1 SERVICE -->Entró a SERVICE getDetails");
        ArrayList<RequestDetail> details = detailsFeignClient.details();
        return details;
    }

    // Trae las repairs del MS car-repair-service
    public ArrayList<RequestRepair> getRepairs() {
        // System.out.println(" EN REPORT 1 SERVICE -->Entró a SERVICE getRepairs");
        ArrayList<RequestRepair> repairs = repairFeignClient.repairs();
        return repairs;
    }

    // Obtiene el atributo repairNames de una lista de details
    public ArrayList<String> getRepairNamesForDetails(ArrayList<RequestDetail> details) {
        System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE getRepairNamesForDetails");
        System.out.println("details: " + details);
        ArrayList<String> repairNames = new ArrayList<>();
        System.out.println("repairNames: " + repairNames);

        for (RequestDetail detail : details) {
            System.out.println(" Entro al primer for de getRepairNamesForDetails");
            System.out.println("detail: " + detail);
            List<String> repairName = detail.getRepairNames();
            System.out.println("repairName: " + repairName);
            if (repairName != null) {
                for (String name : repairName) {
                    System.out.println(" Entro al segundo for de getRepairNamesForDetails");
                    System.out.println("name: " + name);
                    repairNames.add(name);
                }
            }
        }
        System.out.println("repairNames: " + repairNames);
        System.out.println("--> SALIÓ DE SERVICE getRepairNamesForDetails");
        return repairNames;
    }

    // Obtiene el atributo repairAmounts de una lista de details
    public ArrayList<Integer> getRepairAmountsForDetails(ArrayList<RequestDetail> details) {
        // System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE
        // getRepairAmountsForDetails");
        ArrayList<Integer> repairAmounts = new ArrayList<>();
        for (RequestDetail detail : details) {
            List<Integer> repairAmount = detail.getRepairAmounts();
            if (repairAmount != null) {
                for (Integer amount : repairAmount) {
                    repairAmounts.add(amount);
                }
            }
        }
        // System.out.println("repairAmounts: " + repairAmounts);
        // System.out.println("--> SALIÓ DE SERVICE getRepairAmountsForDetails");
        return repairAmounts;
    }

    // Obtiene el atributo name de Repair
    public ArrayList<String> getRepairNames() {
        // System.out.println(" EN REPORT 1 SERVICE --> Entró a SERVICE
        // getRepairNames");
        ArrayList<RequestRepair> repairs = getRepairs();
        Set<String> repairNamesSet = new HashSet<>();
        for (RequestRepair repairObj : repairs) {
            repairNamesSet.add(repairObj.getName());
        }
        // System.out.println("repairNames: " + repairNames);
        // System.out.println("--> SALIÓ DE SERVICE getRepairNames");
        return new ArrayList<>(repairNamesSet);
    }

    // Método report2
    public List<Report2Entity> report2(String month, String year) {
        System.out.println(" EN REPORT 2 SERVICE --> Entró a SERVICE report2");
        ArrayList<Report2Entity> report2 = new ArrayList<>();
        System.out.println("report2: " + report2);
        ArrayList<RequestDetail> details = getDetails();
        System.out.println("details: " + details);
        // recorrer details y obtener admissionDate:
        if (!details.isEmpty()) {
            Iterator<RequestDetail> iterator = details.iterator();
            while (iterator.hasNext()) {
                RequestDetail detail = iterator.next();
                Date admissionDate = detail.getAdmissionDate();
                System.out.println("admissionDate: " + admissionDate);
                // imprimir el tipo de dato de admissionDate
                System.out.println("Tipo de dato de admissionDate: " + admissionDate.getClass().getName());
                // parsear admissionDate a Date

                //SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(admissionDate);

                // Extraer el año y el mes
                String admissionYear = Integer.toString(calendar.get(Calendar.YEAR));
                System.out.println("admissionYear: " + admissionYear);
                System.out.println("Tipo de dato de admissionYear: " + admissionYear.getClass().getName());

                String admissionMonth = String.format("%02d", calendar.get(Calendar.MONTH) + 1);
                System.out.println("admissionMonth: " + admissionMonth);
                System.out.println("Tipo de dato de admissionMonth: " + admissionMonth.getClass().getName());

                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

                System.out.println("month: " + month);
                System.out.println("Tipo de dato de month: " + month.getClass().getName());

                System.out.println("year: " + year);
                System.out.println("Tipo de dato de year: " + year.getClass().getName());

                // comparar mes y año de admissionDate con los parámetros month y year
                if (!admissionMonth.equals(month) || !admissionYear.equals(year)) {
                    System.out.println("entro al if de !admissionMonth.equals(month) || !admissionYear.equals(year)");
                    // sacar de details
                    iterator.remove();
                    System.out.println("details: " + details);
                }

            }

        }
        // Si details está vacío, retornar un arreglo vacío
        if (details.isEmpty()) {
            System.out.println("entro al if , details está vacío");
            System.out.println("report2: " + report2);
            return report2;
        } else {

            ArrayList<String> repairNames = getRepairNamesForDetails(details);// nombres de las reparaciones
                                                                              // que se han hecho
            System.out.println("repairNames: " + repairNames);
            ArrayList<Integer> repairAmounts = getRepairAmountsForDetails(details);// valores de las
                                                                                   // reparaciones que se han
                                                                                   // hecho
            System.out.println("repairAmounts: " + repairAmounts);
            ArrayList<String> baseRepairNames = getRepairNames();// nombres de todas las reparaciones que existen
            System.out.println("baseRepairNames: " + baseRepairNames);

            for (String baseRepairName : baseRepairNames) {
                System.out.println("---------------------Entró al for de baseRepairNames");
                System.out.println("baseRepairName: " + baseRepairName);

                int nRepairedCars = 0;
                double nAmountRepairedCars = 0;

                for (String repairName : repairNames) {
                    System.out.println("---------------------Entró al for de repairNames");
                    System.out.println("repairName: " + repairName);
                    if (baseRepairName.equals(repairName)) {
                        System.out.println("---------------------Entró al if de baseRepairName.equals(repairName)");
                        System.out.println("baseRepairName: " + baseRepairName);
                        System.out.println("repairName: " + repairName);
                        // se crea un contador para contar cuantas veces se repite una reparación
                        nRepairedCars++;
                        System.out.println("nRepairedCars: " + nRepairedCars);
                        nAmountRepairedCars = nAmountRepairedCars + repairAmounts.get(repairNames.indexOf(repairName));
                        System.out.println("nAmountRepairedCars: " + nAmountRepairedCars);

                        // se crea un objeto Report2Entity
                        Report2Entity report2Entity = new Report2Entity();
                        report2Entity.setRepairName(baseRepairName);
                        report2Entity.setNRepairedCars(nRepairedCars);
                        report2Entity.setAmountRepairedCars(nAmountRepairedCars);

                        report2.add(report2Entity);
                        System.out.println("Cada reporte individual -->report2Entity: " + report2Entity);

                    }
                }

            }
        }
        System.out.println("Todos los reportes que debería hacer --> report2: " + report2);
        System.out.println("--> SALIÓ DE SERVICE report2");
        return report2;

    }

    public List<Report2Entity> report2FromOriginMonth(String month, String year, int var) {
        System.out.println(" EN REPORT 2 SERVICE --> Entró a SERVICE var");

        // Reporte del mes original
        List<Report2Entity> report2 = report2(month, year);
        System.out.println("report2: " + report2);

        int monthInt = Integer.parseInt(month);
        // Calcular 1 mes anterior
        int monthAnt = monthInt - var;

        String monthAntStr = String.format("%02d", monthAnt);

        System.out.println("monthAntStr: " + monthAntStr);

        // Reporte de los 3 meses anteriores
        List<Report2Entity> report2Ant = report2(monthAntStr, year);

        System.out.println("report2Ant: " + report2Ant);
        return report2Ant;
    }

    // Función que revisa 2 listas de reportes y crea una nueva lista en donde estén
    // todos los reportes de la lista 1 y los reportes de la lista 2 que no estén en
    // la lista 1
    public List<Report2Entity> mergeReports(List<Report2Entity> report2, List<Report2Entity> report2Ant) {
        System.out.println(" EN REPORT 2 SERVICE --> Entró a SERVICE mergeReports");

        List<Report2Entity> mergedReports = new ArrayList<>();
        System.out.println("mergedReports: " + mergedReports);
        List<String> mergeNames = new ArrayList<>();

        for (Report2Entity report : report2) {
            System.out.println("---------------------Entró al for de report2");
            System.out.println("report: " + report);
            mergedReports.add(report);
            mergeNames.add(report.getRepairName());
        }
        for (Report2Entity reportAnt : report2Ant) {
            System.out.println("---------------------Entró al for de report2Ant");
            System.out.println("reportAnt: " + reportAnt);
            String name = reportAnt.getRepairName();
            if (!mergeNames.contains(name)) {
                mergedReports.add(reportAnt);
            }
        }
        System.out.println("Todos los reportes que debería hacer --> mergedReports: " + mergedReports);
        System.out.println("--> SALIÓ DE SERVICE mergeReports");
        return mergedReports;
    }

    // Función que calcula el porcentaje de variación de los reportes
    public List<Report2Aux> calculatePercentageVar(List<Report2Entity> report2, List<Report2Entity> report2Ant,
            List<Report2Entity> mergedReports) {
        System.out.println(" EN REPORT 2 SERVICE --> Entró a SERVICE calculatePercentageVar");

        // Obtener nombres de report:
        List<String> report2Names = new ArrayList<>();
        for (Report2Entity report : report2) {
            report2Names.add(report.getRepairName());
        }

        // Obtener nombres de report2Ant:
        List<String> report2AntNames = new ArrayList<>();
        for (Report2Entity reportAnt : report2Ant) {
            report2AntNames.add(reportAnt.getRepairName());
        }

        List<Report2Aux> report2AuxList = new ArrayList<>();

        for (Report2Entity report : mergedReports) {
            System.out.println("---------------------Entró al for de mergedReports");
            System.out.println("report: " + report);
            Report2Aux report2Aux = new Report2Aux();

            if (report2Names.contains(report.getRepairName()) && report2AntNames.contains(report.getRepairName())) {
                //int indexReport = report2Names.indexOf(report.getRepairName());
                int indexReportAnt = report2AntNames.indexOf(report.getRepairName());
                double nRepairedCars = report.getNRepairedCars();
                double nRepairedCarsAnt = report2Ant.get(indexReportAnt).getNRepairedCars();
                double percentageVar = ((nRepairedCars - nRepairedCarsAnt) / nRepairedCarsAnt) * 100;
                report2Aux.setNPercentageVar(percentageVar);
                double amountRepairedCars = report.getAmountRepairedCars();
                double amountRepairedCarsAnt = report2Ant.get(indexReportAnt).getAmountRepairedCars();
                double amountPercentageVar = ((amountRepairedCars - amountRepairedCarsAnt) / amountRepairedCarsAnt)
                        * 100;
                report2Aux.setAmountPercentageVar(amountPercentageVar);
                report2Aux.setRepairName(report.getRepairName());


            } else if (report2Names.contains(report.getRepairName()) ) {
                report2Aux.setNPercentageVar(0.0);
                report2Aux.setAmountPercentageVar(0.0);
                report2Aux.setRepairName(report.getRepairName());

            } else if (report2AntNames.contains(report.getRepairName())) {
                report2Aux.setNPercentageVar(-100.0);
                report2Aux.setAmountPercentageVar(-100.0);
                report2Aux.setRepairName(report.getRepairName());
            }

            report2AuxList.add(report2Aux);

        }

        System.out.println("Todos los reportes que debería hacer --> report2AuxList: " + report2AuxList);
        System.out.println("--> SALIÓ DE SERVICE calculatePercentageVar");
        return report2AuxList;

    }

    // Método final para report2
    public List<List<Report2Aux>> report2Final(String month, String year) {
        System.out.println(" EN REPORT 2 SERVICE --> Entró a SERVICE report2Final");

        List<List<Report2Aux>> report2Final = new ArrayList<>();

        // Reporte del mes original
        List<Report2Entity> report2 = report2(month, year);
        System.out.println("report2: " + report2);
        report2 = filterReports3(report2);

        //
        List<Report2Entity> report2Ant = report2FromOriginMonth(month, year, 1);
        System.out.println("report2Ant: " + report2Ant);
        report2Ant = filterReports3(report2Ant);

        List<Report2Entity> report2Ant2 = report2FromOriginMonth(month, year, 2);
        System.out.println("report2Ant2: " + report2Ant2);
        report2Ant2 = filterReports3(report2Ant2);

        List<Report2Entity> report2Ant3 = report2FromOriginMonth(month, year, 3);
        System.out.println("report2Ant3: " + report2Ant3);
        report2Ant3 = filterReports3(report2Ant3);

        // Unir los reportes
        List<Report2Entity> mergedReports = mergeReports(report2, report2Ant);
        System.out.println("mergedReports: " + mergedReports);

        List<Report2Entity> mergedReports2 = mergeReports(report2, report2Ant2);
        System.out.println("mergedReports2: " + mergedReports2);

        List<Report2Entity> mergedReports3 = mergeReports(report2, report2Ant3);
        System.out.println("mergedReports3: " + mergedReports3);

        // Calcular el porcentaje de variación
        List<Report2Aux> report2AuxListOriginal = calculatePercentageVar(report2, report2, report2);

        List<Report2Aux> report2AuxList = calculatePercentageVar(report2, report2Ant, mergedReports);
        System.out.println("report2AuxList: " + report2AuxList);

        List<Report2Aux> report2AuxList2 = calculatePercentageVar(report2, report2Ant2, mergedReports2);
        System.out.println("report2AuxList2: " + report2AuxList2);

        List<Report2Aux> report2AuxList3 = calculatePercentageVar(report2, report2Ant3, mergedReports3);
        System.out.println("report2AuxList3: " + report2AuxList3);

        System.out.println("--> SALIÓ DE SERVICE report2Final");

        report2Final.add(report2AuxListOriginal);
        report2Final.add(report2AuxList);
        report2Final.add(report2AuxList2);
        report2Final.add(report2AuxList3);
        return report2Final;
    }


    // Método para los reportes
    public List<List<Report2Entity>> report2Alls(String month, String year) {
        System.out.println(" EN REPORT 2 SERVICE --> Entró a SERVICE report2Final");

        List<List<Report2Entity>> report2Final = new ArrayList<>();

        // Reporte del mes original
        List<Report2Entity> report2 = report2(month, year);
        System.out.println("report2: " + report2);

        //
        List<Report2Entity> report2Ant = report2FromOriginMonth(month, year, 1);
        System.out.println("report2Ant: " + report2Ant);

        List<Report2Entity> report2Ant2 = report2FromOriginMonth(month, year, 2);
        System.out.println("report2Ant2: " + report2Ant2);

        List<Report2Entity> report2Ant3 = report2FromOriginMonth(month, year, 3);
        System.out.println("report2Ant3: " + report2Ant3);

        report2Final.add(report2);
        report2Final.add(report2Ant);
        report2Final.add(report2Ant2);
        report2Final.add(report2Ant3);

        System.out.println("--> SALIÓ DE SERVICE report2Final");

        return report2Final;

    }

    public List<List<Report2Entity>> filterReports2(List<List<Report2Entity>> reportLists) {
        System.out.println(" EN REPORT 2 SERVICE --> Entró a SERVICE filterReports2");

        List<List<Report2Entity>> filteredReportLists = new ArrayList<>();

        for (List<Report2Entity> reports : reportLists) {
            Map<String, Report2Entity> map = new HashMap<>();
            for (Report2Entity report : reports) {
                String repairName = report.getRepairName();
                if (!map.containsKey(repairName) || map.get(repairName).getNRepairedCars() < report.getNRepairedCars()) {
                    map.put(repairName, report);
                }
            }
            filteredReportLists.add(new ArrayList<>(map.values()));
        }

        return filteredReportLists;
    }

    public List<Report2Entity> filterReports3(List<Report2Entity> reportLists) {
        System.out.println(" EN REPORT 2 SERVICE --> Entró a SERVICE filterReports2");

        Map<String, Report2Entity> map = new HashMap<>();

            for (Report2Entity report : reportLists) {
                String repairName = report.getRepairName();
                if (!map.containsKey(repairName) || map.get(repairName).getNRepairedCars() < report.getNRepairedCars()) {
                    map.put(repairName, report);
                }
            }

        return new ArrayList<>(map.values());
    }


}