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
 * @author
 */

@Path("/area")
@Api("/Area Service")
@SwaggerDefinition(tags = {@Tag(name = "Area Service", description = "RESTful API to enable a user to get area of different figures.")})
public class AreaResource {

    private MathContext mc = new MathContext(10);

    @GET
    @Path("/circle/{radius}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Area of A circle", produces = MediaType.APPLICATION_JSON, httpMethod = "GET", notes = "Area of A circle", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Circle Area", response = String.class)
    })
    public Response circleArea(@ApiParam(value = "Radius of circle", name = "radius", required = true) @PathParam("radius") BigDecimal radius) {
        BigDecimal pi = new BigDecimal(Math.PI, mc);

        BigDecimal area = radius.pow(2).multiply(pi, mc);

        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("Figure", "Circle");
        res.put("Raduis", radius);
        res.put("MathPI", pi);
        res.put("area", area);
        return Response.status(200).entity(res.toString()).build();
    }

    @GET
    @Path("/triangle/{base}/{height}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Area of A triangle")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Triangle Area", response = String.class)
    })
    public Response triangleArea(@ApiParam(value = "Base of Triangle", name = "base", required = true) @PathParam("base") BigDecimal base,
            @ApiParam(value = "Height of Triangle", name = "height", required = true) @PathParam("height") BigDecimal height) {
        BigDecimal factor = new BigDecimal(0.5, mc);

        BigDecimal area = factor.multiply((base.multiply(height)), mc);

        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("Figure", "Triangle");
        res.put("Base", base);
        res.put("Height", height);
        res.put("area", area);
        return Response.status(200).entity(res.toString()).build();
    }

    @GET
    @Path("/rectangle/{width}/{height}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Area of A Rectangle")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Rectangle Area", response = String.class)
    })
    public Response rectangleArea(@ApiParam(value = "Width of Rectangle", name = "width", required = true) @PathParam("width") BigDecimal width, 
            @ApiParam(value = "Height of Rectangle", name = "height", required = true) @PathParam("height") BigDecimal height) {

        BigDecimal area = width.multiply(height, mc);

        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("Figure", "Rectangle");
        res.put("Width", width);
        res.put("Height", height);
        res.put("area", area);
        return Response.status(200).entity(res.toString()).build();
    }

    @GET
    @Path("/cuboid/{width}/{length}/{height}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Surface Area of A cuboid")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Surface Area of A cuboid", response = String.class)
    })
    public Response closedCuboidSurfaceArea(@ApiParam(value = "Width of cuboid", name = "width", required = true) @PathParam("width") BigDecimal width, 
            @ApiParam(value = "Length of cuboid", name = "length", required = true) @PathParam("length") BigDecimal length, @ApiParam(value = "Height of cuboid", name = "height", required = true) @PathParam("height") BigDecimal height) {
        BigDecimal widthlength = width.multiply(length).multiply(new BigDecimal(2), mc);
        BigDecimal heightwidth = height.multiply(width).multiply(new BigDecimal(2), mc);
        BigDecimal heigthlength = height.multiply(length).multiply(new BigDecimal(2), mc);

        BigDecimal area = widthlength.add(heightwidth).add(heigthlength);

        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("figure", "Closed-Cuboid");
        res.put("width", width);
        res.put("length", length);
        res.put("height", height);
        res.put("area", area);
        return Response.status(200).entity(res.toString()).build();
    }

    @GET
    @Path("/trapezium/{a}/{b}/{height}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Area of A trapezium")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Trapezium Area", response = String.class)
    })
    public Response trapeziumArea(@ApiParam(value = "Base a", name = "a", required = true) @PathParam("a") BigDecimal a, @ApiParam(value = "Base b", name = "b", required = true) @PathParam("b") BigDecimal b, 
            @ApiParam(value = "Trapezium  Height", name = "height", required = true) @PathParam("height") BigDecimal height) {
        BigDecimal factor = new BigDecimal(0.5, mc);
        BigDecimal area = factor.multiply((a.add(b))).multiply(height, mc);

        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("Figure", "Trapezium");
        res.put("a", a);
        res.put("b", b);
        res.put("Height", height);
        res.put("area", area);
        return Response.status(200).entity(res.toString()).build();
    }

    @GET
    @Path("/cylinder/{radius}/{height}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Surface Area of Cylinder")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Surface Area of Cylinder", response = String.class)
    })
    public Response cylinderArea(@ApiParam(value = "Radius of Cylinder", name = "radius", required = true) @PathParam("radius") BigDecimal radius, 
            @ApiParam(value = "Height of Cylinder", name = "height", required = true) @PathParam("height") BigDecimal height) {
        BigDecimal pi = new BigDecimal(Math.PI, mc);
        BigDecimal circularArea = radius.pow(2).multiply(pi).multiply(new BigDecimal(2), mc);
        BigDecimal diamond = radius.multiply(new BigDecimal(2));
        BigDecimal circumference = pi.multiply(diamond);
        BigDecimal rectArea = circumference.multiply(height, mc);

        BigDecimal area = rectArea.add(circularArea);

        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("figure", "cylinder");
        res.put("raduis", radius);
        res.put("height", height);
        res.put("MathPI", pi);
        res.put("area", area);
        return Response.status(200).entity(res.toString()).build();
    }

}
