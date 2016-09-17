package com.anncode.aplicacioncontactos.GCM.Notifications.model;

/**
 * Created by isaachernandezquinonez on 12/07/16.
 */
public class UsuarioResponse {
    private String id;
    private String tokenDevices;
    private String Idinstagram;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UsuarioResponse() {
    }

    public UsuarioResponse(String id ,String tokenDevices, String Idinstagram) {
        this.id = id;
        this.tokenDevices = tokenDevices;
        this.Idinstagram = Idinstagram;
    }

    public String getTokenDevices() {
        return tokenDevices;
    }

    public void setTokenDevices(String tokenDevices) {
        this.tokenDevices = tokenDevices;
    }

    public String getIdinstagram() {
        return Idinstagram;
    }

    public void setIdinstagram(String idinstagram) {
        Idinstagram = idinstagram;
    }
}
