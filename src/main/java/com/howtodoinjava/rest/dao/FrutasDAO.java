package com.howtodoinjava.rest.dao;

import com.howtodoinjava.rest.dto.FrutaDTO;
import com.howtodoinjava.rest.dto.ListadoDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class FrutasDAO {

    public List<FrutaDTO> getFrutas() {

        List<FrutaDTO> resultFrutas = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/frutapp?serverTimezone=UTC", "android", "frutapp");
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from frutas");
            resultFrutas = new ArrayList<>();
            while (rs.next()) {
                FrutaDTO fruta = new FrutaDTO();
                fruta.setId(rs.getInt("id"));
                fruta.setNombre(rs.getString("nombre"));
                fruta.setDisponible(rs.getBoolean("disponible"));
                fruta.setStockInicial(rs.getInt("stock_inicial"));
                fruta.setStockReal(rs.getInt("stock_actual"));
                resultFrutas.add(fruta);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultFrutas;
    }

    public FrutaDTO buscarFruta(Integer idFruta) {
        FrutaDTO frutaElegida = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/frutapp?serverTimezone=UTC", "android", "frutapp");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from frutas where id = "+idFruta);
            while (rs.next()) {
                FrutaDTO fruta = new FrutaDTO();
                fruta.setId(rs.getInt("id"));
                fruta.setNombre(rs.getString("nombre"));
                fruta.setDisponible(rs.getBoolean("disponible"));
                fruta.setStockInicial(rs.getInt("stock_inicial"));
                fruta.setStockReal(rs.getInt("stock_actual"));
                frutaElegida = new FrutaDTO();
                frutaElegida = fruta;
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return frutaElegida;
    }

    public void agregarFrutasToList(Integer idFruta, Integer idList, Integer cantidad) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date hoy = new Date();
        String marca = sdf.format(hoy);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/frutapp?serverTimezone=UTC", "android", "frutapp");

            String query = "insert into listado (id_lista,id_fruta,timestamp,creador,cantidad) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idFruta);
            preparedStatement.setInt(2, idList);
            preparedStatement.setString(3, marca);
            preparedStatement.setInt(4, 1);
            preparedStatement.setInt(5,cantidad);
            preparedStatement.execute();
            this.disminuirStockActual(idFruta,cantidad);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void quitarFrutaOfList(Integer idFruta, Integer idList, Integer cantidad) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/frutapp?serverTimezone=UTC", "android", "frutapp");

            String query = "delete from listado where id_fruta = ? and id_lista = ? and cantidad = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idFruta);
            preparedStatement.setInt(2, idList);
            preparedStatement.setInt(3, cantidad);
            preparedStatement.execute();
            this.aumentarStockActual(idFruta, cantidad);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void disminuirStockActual(Integer idFruta, Integer cantidad) {

        FrutaDTO frutaDTO = this.buscarFruta(idFruta);
        Integer nuevoStock = frutaDTO.getStockReal()-cantidad;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/frutapp?serverTimezone=UTC", "android", "frutapp");

            String query = "update frutas set stock_actual = ? where id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, nuevoStock);
            preparedStatement.setInt(2, idFruta);
            preparedStatement.execute();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<ListadoDTO> getMiListado(Integer idList) {

        List<ListadoDTO> resultFrutas = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/frutapp?serverTimezone=UTC", "android", "frutapp");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from listado where id_lista = "+idList);
            resultFrutas = new ArrayList<>();
            while (rs.next()) {
                ListadoDTO miLista = new ListadoDTO();
                miLista.setId(rs.getInt("id_lista"));
                FrutaDTO fruta = this.buscarFruta(rs.getInt("id_fruta"));
                miLista.setFruta(fruta);
                miLista.setCreador(rs.getInt("creador"));
                miLista.setCantidad(rs.getInt("cantidad"));
                resultFrutas.add(miLista);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultFrutas;
    }

    private void aumentarStockActual(Integer idFruta, Integer cantidad) {

        FrutaDTO frutaDTO = this.buscarFruta(idFruta);
        Integer nuevoStock = frutaDTO.getStockReal()+cantidad;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/frutapp?serverTimezone=UTC", "android", "frutapp");

            String query = "update frutas set stock_actual = ? where id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, nuevoStock);
            preparedStatement.setInt(2, idFruta);
            preparedStatement.execute();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
