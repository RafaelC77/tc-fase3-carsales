package tech.challenge.controllers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tech.challenge.dtos.CreateCarRequestDTO;
import tech.challenge.entities.CarEntity;
import tech.challenge.services.CarService;

import java.util.UUID;

@Path("/cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarController {

    @Inject
    CarService carService;

    @POST
    @Transactional
    public Response createCar(CreateCarRequestDTO requestDTO) {
        try {
            CarEntity createdCar = carService.createCar(requestDTO);
            return Response.status(Response.Status.CREATED).entity(createdCar).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao cadastrar veículo: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{carId}")
    @Transactional
    public Response updateCar(@PathParam("carId") UUID carId, CarEntity updatedCar) {
        try {
            CarEntity car = carService.updateCar(carId, updatedCar);
            if (car != null) {
                return Response.ok(car).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Veículo não encontrado").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao atualizar veículo: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("available")
    public Response listAvailable() {
        var carList = carService.listAvailableCars();
        return Response.status(Response.Status.OK).entity(carList).build();
    }

    @GET
    @Path("sold")
    public Response listSold() {
        var carList = carService.listSoldCars();
        return Response.status(Response.Status.OK).entity(carList).build();
    }
}
