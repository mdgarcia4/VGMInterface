package com.vgmsistemas.vgminterface.restController.unilever;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vgmsistemas.vgminterface.entity.unilever.CuentaCliente;
import com.vgmsistemas.vgminterface.entity.unilever.Views;
import com.vgmsistemas.vgminterface.service.unilever.CuentaClienteService;
import com.vgmsistemas.vgminterface.util.RutasServicios;
//import org.apache.commons.lang.math.NumberUtils;
import java.io.IOException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@PreAuthorize("authenticated")
@RestController
@RequestMapping(RutasServicios.CUENTA_CLIENTE_RESPUESTA)
public class CuentaClienteRespRestController {
	private static Logger LOG = LoggerFactory.getLogger(CuentaClienteRespRestController.class)	;
	
	@Autowired
	CuentaClienteService cuentaClienteService;
	
		
	@PostMapping()
	@JsonView(Views.Response.class)
	public CuentaCliente respuestaCuentaCliente(@RequestBody String clieString) throws Exception {
		//Gson gson = new Gson();
		
		Gson gson =
                new GsonBuilder()
                        .registerTypeAdapter(Integer.class, new EmptyStringToIntegerTypeAdapter())
                        .registerTypeAdapter(Float.class, new EmptyStringToFloatTypeAdapter())
                        .create();
		
		
		JSONObject jsonClie = new JSONObject(clieString);
		LOG.info("CuentaCliente respuestaCuentaCliente(). Json: " + clieString);
		CuentaCliente cuentaCliente = gson.fromJson(jsonClie.toString(), CuentaCliente.class);
		
		cuentaCliente = cuentaClienteService.actualizarEnviado(cuentaCliente);
		return cuentaCliente;
	}
	
	public static class EmptyStringToFloatTypeAdapter extends TypeAdapter<Number> {
		
        @Override
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            if (number == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.value(number);
        }

        @Override
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }

            try {
                String value = jsonReader.nextString();
                if ("".equals(value)) {
                    return (float) 0.0;
                }
                return NumberUtils.parseNumber(value, Float.class);
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

    }
	
public static class EmptyStringToIntegerTypeAdapter extends TypeAdapter<Number> {
		
        @Override
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            if (number == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.value(number);
        }

        @Override
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }

            try {
                String value = jsonReader.nextString();
                if ("".equals(value)) {
                    return 0;
                }
                return NumberUtils.parseNumber(value, Integer.class);
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

    }
}