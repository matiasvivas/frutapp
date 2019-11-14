package com.howtodoinjava.rest.controller;

import com.howtodoinjava.rest.dao.FrutasDAO;
import com.howtodoinjava.rest.dto.FrutaDTO;
import com.howtodoinjava.rest.dto.ListadoDTO;
import com.howtodoinjava.rest.model.login.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "/app")
public class FrutasController {
    @Autowired
    private FrutasDAO frutasDAO;

    @GetMapping(path = "/getFrutas", produces = "application/json")
    public List<FrutaDTO> getFrutas() {

        return frutasDAO.getFrutas();
    }

    @GetMapping(path = "/agregar/{cantidad}/{idFruta}/to/{idList}", produces = "application/json")
    public ResponseEntity<Object> agregarToList(
            @PathVariable(value = "idFruta") Integer idFruta,
            @PathVariable(value = "idList") Integer idList,
            @PathVariable(value = "cantidad") Integer cantidad){

        FrutaDTO frutaElegida = frutasDAO.buscarFruta(idFruta);
        if(!frutaElegida.getDisponible())
            return new ResponseEntity<Object>("La fruta no esta disponible", HttpStatus.BAD_REQUEST);
        if(frutaElegida.getStockReal()<=0)
            return new ResponseEntity<Object>("Ya consumieron todas estas frutas", HttpStatus.BAD_REQUEST);

        frutasDAO.agregarFrutasToList(idFruta, idList, cantidad);
        return new ResponseEntity<Object>("Frutas agregadas", HttpStatus.OK);
    }

    @GetMapping(path = "/quitar/{cantidad}/{idFruta}/of/{idList}", produces = "application/json")
    public ResponseEntity<Object> quitarOfList(
            @PathVariable(value = "idFruta") Integer idFruta,
            @PathVariable(value = "idList") Integer idList,
            @PathVariable(value="cantidad") Integer cantidad){
        frutasDAO.quitarFrutaOfList(idFruta, idList, cantidad);
        return new ResponseEntity<Object>("Quitaste las frutas", HttpStatus.OK);
    }

    @GetMapping(path = "/getMiListado/{idList}", produces = "application/json")
    public List<ListadoDTO> getMiListado(
            @PathVariable(value = "idList") Integer idList) {
        return frutasDAO.getMiListado(idList);
    }
}
