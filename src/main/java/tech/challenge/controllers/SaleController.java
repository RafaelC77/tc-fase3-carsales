package tech.challenge.controllers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tech.challenge.entities.SaleEntity;
import tech.challenge.services.SaleService;

@Path("/sales")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SaleController {

    @Inject
    SaleService saleService;

    @POST
    @Transactional
    public Response createSale(SaleEntity sale) {
        try {
            SaleEntity newSale = saleService.createSale(sale);
            return Response.status(Response.Status.CREATED)
                    .entity(newSale)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao criar venda: " + e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/{saleId}")
    @Transactional
    public Response confirmSale(@PathParam("saleId") String saleId) {
        try {
            SaleEntity confirmedSale = saleService.confirmSale(saleId);
            if (confirmedSale != null) {
                return Response.ok(confirmedSale).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Venda não encontrada").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao confirmar venda: " + e.getMessage()).build();
        }
    }
}
