package com.milkaxe_studios.clinicaapp.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ReqExame {

    public String
            Id,
            IdConsulta,
            descReqExame,
            dataReqExame;

    public ReqExame() {}

    public ReqExame(String idConsulta, String descReqExames, String dataReqExames) {
        IdConsulta = idConsulta;
        this.descReqExame = descReqExames;
        this.dataReqExame = dataReqExames;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Id", Id);
            jsonObject.put("IdConsulta", IdConsulta);
            jsonObject.put("descReqExame", descReqExame);
            jsonObject.put("dataReqExame", dataReqExame);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static ReqExame getReqExameFromJSON(String jsonString) {
        ReqExame reqExame = new ReqExame();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            reqExame.Id = jsonObject.getString("Id");
            reqExame.IdConsulta = jsonObject.getString("IdConsulta");
            reqExame.descReqExame = jsonObject.getString("descReqExame");
            reqExame.dataReqExame = jsonObject.getString("dataReqExame");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reqExame;
    }

}
