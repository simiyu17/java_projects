/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maths.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import java.math.BigDecimal;
import java.math.MathContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author simiyu
 */
@Path("/volume")
@Api("/Volume Service")
@SwaggerDefinition(tags = {@Tag(name = "Volume Service", description = "RESTful API to enable a user to get Volume of different figures.")})
public class VolumeResource {
    
    private MathContext mc = new MathContext(10); 
    
    @GET
    @Path("/cuboid/{width}/{length}/{height}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Area of A circle")
    @ApiResponses(value = {
        @ApiResponse(code=200, message="Volume of A cuboid",  response = String.class)
    })
    public Response closedCuboidSurfaceArea(@ApiParam(value = "Width of cuboid", name = "width", required = true) @PathParam("width") BigDecimal width, 
            @ApiParam(value = "Length of cuboid", name = "length", required = true) @PathParam("length") BigDecimal length, @ApiParam(value = "Heigth of cuboid", name = "height", required = true) @PathParam("height") BigDecimal height){
        
        BigDecimal volume = width.multiply(length).multiply(height, mc);
        
        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("figure", "Closed-Cuboid");
        res.put("width", width);
        res.put("length", length);
        res.put("height", height);
        res.put("volume", volume);
        return Response.status(200).entity(res.toString()).build();
    }
    
    
    @GET
    @Path("/cylinder/{radius}/{height}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Area of A circle")
    @ApiResponses(value = {
        @ApiResponse(code=200, message="Volume of Cylinder",  response = String.class)
    })
    public Response cylinderArea(@ApiParam(value = "Radius of Cylinder", name = "radius", required = true) @PathParam("radius") BigDecimal radius, 
            @ApiParam(value = "Height of Cylinder", name = "height", required = true) @PathParam("height") BigDecimal height){
        BigDecimal pi = new BigDecimal(Math.PI, mc);
        BigDecimal baseArea = radius.pow(2).multiply(pi, mc);
        
        //base area by height
        BigDecimal volume = baseArea.add(height);
        
        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("figure", "cylinder");
        res.put("raduis", radius);
        res.put("height", height);
        res.put("MathPI", pi);
        res.put("volume", volume);
        return Response.status(200).entity(res.toString()).build();
    }
    
}
