package com.example.hospital.helper;


import com.example.hospital.entity.Appointment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppointmentHelper{
        public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";


        public static boolean checkExcelFormatOfAppointment(MultipartFile file)
        {

            if (!TYPE.equals(file.getContentType())) {
                return false;
            }

            return true;
        }


        public static List<Appointment> convertExcelToListOfAppointment(InputStream is)
        {
            try
            {
                Workbook workbook= new XSSFWorkbook(is);
                Sheet sheet= workbook.getSheetAt(0);

                Iterator<Row> rows=sheet.iterator();
                List<Appointment>list=new ArrayList<Appointment>();
                int rowNumber=0;

                while (rows.hasNext())
                {
                    Row currentRow= rows.next();
                    if(rowNumber==0)
                    {
                        rowNumber++;
                        continue;
                    }
                    Iterator<Cell> cellsInRow=currentRow.iterator();
                    Appointment appointment=new Appointment();
                    int cid=0;

                    while (cellsInRow.hasNext())
                    {
                        Cell currentCell=cellsInRow.next();
                        switch (cid)
                        {
                            case 0:
//                                appointment.setPatient_id(currentCell.getStringCellValue());
                                break;
                            case 1:
                                appointment.setAppointment_date(currentCell.getDateCellValue());
                                break;
                            case 2:
                                appointment.setAppointment_time(currentCell.getStringCellValue());
                                break;
                            case 3:
                                appointment.setPayment_id(currentCell.getStringCellValue());
                                break;
                            case 4:
                                appointment.setAppointment_status(currentCell.getStringCellValue());
                                break;
                            case 5:
                                appointment.setDoctor_id(currentCell.getStringCellValue());
                                break;
                            case  6:
                                appointment.setPatient_id( currentCell.getStringCellValue());
                                break;
                            case 7:
                                appointment.setHospital_id(currentCell.getStringCellValue());
                                break;

                            default:
                                System.out.println("end");
                                break;
                        }
                        cid++;
                    }
                    list.add(appointment);
                }
                workbook.close();
                return list;
            }
            catch ( IOException e)
            {
                throw new RuntimeException("fail to parse Excel file: " + e.getMessage());

            }

        }
    }


