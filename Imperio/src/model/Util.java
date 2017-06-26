/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author Will
 */
public class Util {

    public static Usuario usuario;

    public static Config c;

    public static String convertedataUS(String data) {
        data = data.replace("/", "");

        if (data.equals("")) {
            return data;
        } else {

            String ano = data.substring(4, 8);
            String mes = data.substring(2, 4);
            String dia = data.substring(0, 2);
            return ano + mes + dia;
        }
    }

    public static String convertedataBR(String data) {
        if (data.equals("")) {
            return data;
        } else {
            data = data.replace("-", "");
            String ano = data.substring(0, 4);
            String mes = data.substring(4, 6);
            String dia = data.substring(6, 8);

            return dia + "/" + mes + "/" + ano;
        }

    }

    public static String formataCPF(String cpf) {
        if (cpf.length() == 11) {
            String p1 = cpf.substring(0, 3);
            String p2 = cpf.substring(3, 6);
            String p3 = cpf.substring(6, 9);
            String p4 = cpf.substring(9, 11);

            return p1 + "." + p2 + "." + p3 + "-" + p4;
        } else {
            //  '97.527.662/0001-56'
            String p1 = cpf.substring(0, 2);
            String p2 = cpf.substring(2, 5);
            String p3 = cpf.substring(5, 8);
            String p4 = cpf.substring(8, 12);
            String p5 = cpf.substring(12, 14);

            return p1 + "." + p2 + "." + p3 + "/" + p4 + "-" + p5;
        }

    }

    public static String limpaCPF(String cpf) {
        cpf = cpf.replace("-", "").replace("/", "").replace(".", "");
        return cpf;
    }

    public static Date dataBR(String data) {
        if (data == null || data.equals("")) {
            return null;
        }
        Date date = null;
        try {
            DateFormat databr = new SimpleDateFormat("dd/MM/yyyy");
            date = (java.util.Date) databr.parse(data);
        } catch (ParseException e) {

        }
        return date;
    }

    public static Date dataUS(String data) {
        if (data == null || data.equals("")) {
            return null;
        }
        Date date = null;
        try {
            DateFormat dataus = new SimpleDateFormat("yyyy/MM/dd");
            date = (java.util.Date) dataus.parse(data);
        } catch (ParseException e) {

        }
        return date;
    }

//        java.util.Date data = tfdtai.getDate();
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//        String novaData = formato.format(data);
    public static String datatoStringUS(java.util.Date data) {
        if (data == null) {
            return "";
        }
        DateFormat dataus = new SimpleDateFormat("yyyy/MM/dd");
        String novaData = dataus.format(data);
        return novaData;
    }

    public static String datatoStringBR(java.util.Date data) {
        if (data == null) {
            return "";
        }
        DateFormat dataus = new SimpleDateFormat("dd/MM/yyyy");
        String novaData = dataus.format(data);
        return novaData;
    }

    public static DateTime DateToDTM(java.util.Date dt) {
        DateTime dtm = new DateTime(dt);

        return dtm;
    }

    public static DateTime StringToDTM(String dt) {
        DateTime dtm = new DateTime(dt);

        return dtm;
    }

    public static int difemdias(DateTime dti, DateTime dtf) {
        int dias = Days.daysBetween(dti, dtf).getDays();

        return dias;
    }

    public static boolean vdtg(Date emissao, Date garantia) {
        boolean data;
        if (emissao == null || garantia == null) {
            data = false;
        } else {
            if (emissao.before(garantia)) {
                data = true;
            } else if (emissao.after(garantia)) {
                data = false;
            } else {
                data = false;
            }
            
        }
        return data;
    }

}
