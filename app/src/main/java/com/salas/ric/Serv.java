package com.salas.ric;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Serv {
    String ruta = "/posts";
    @GET(ruta)
    Call< List<POST> > getPost();
}
