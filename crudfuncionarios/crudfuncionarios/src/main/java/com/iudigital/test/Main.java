package com.iudigital.test;

import com.iudigital.controller.FuncionarioController;
import com.iudigital.domain.Funcionario;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void crear(FuncionarioController funcionarioController) {
        try {
            Scanner sc = new Scanner(System.in);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            System.out.println("Digite la el documento del funcionario");
            Integer documento = sc.nextInt();
            System.out.println("-----------");

            System.out.println("Digite el tipo de documento del funcionario (cédula, pasaporte, cédula de extranjería)");
            String tipoId = sc.nextLine();
            System.out.println("-----------");

            System.out.println("Digite el nombre del funcionario");
            String nombre = sc.nextLine();
            System.out.println("-----------");

            System.out.println("Digite los apellidos del funcionario");
            String apellidos = sc.nextLine();
            System.out.println("-----------");

            System.out.println("Digite el estado civil del funcionario: ");
            String estadocivil = sc.nextLine();
            System.out.println("-----------");

            System.out.println("Digite el sexo del funcionario (M o F): ");
            String sexo = sc.nextLine();
            System.out.println("-----------");

            System.out.println("Digite la dirección del funcionario ");
            String direccion = sc.nextLine();
            System.out.println("-----------");

            System.out.println("Digite el teléfono del funcionario");
            Integer telefono = sc.nextInt();
            System.out.println("-----------");

            System.out.println("Digite el año de nacimiento del funcionario: ");
            String año = sc.next();

            System.out.println("Digite el mes de nacimiento: ");
            String mes = sc.next();

            System.out.println("Digite el día de nacimiento: ");
            String dia = sc.next();


            Date fechaNacimiento = simpleDateFormat.parse(dia + "/" + mes + "/" + año);
            java.sql.Date sqlDate = new java.sql.Date(fechaNacimiento.getTime());



            //Seteamos los valores ingresados por consola a partir de una instancia de Funcionario

            Funcionario func = new Funcionario();

            func.setIdFuncionario(documento);
            func.setTipoId(tipoId);
            func.setNombres(nombre);
            func.setApellidos(apellidos);
            func.setSexo(sexo);
            func.setDireccion(direccion);
            func.setTelefono(telefono);
            func.setFechaNacimiento(sqlDate);
            func.setEstadoCivil(estadocivil);

            funcionarioController.crear(func);

            System.out.println("El funcionario se ha creado con éxito");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void obtenerFuncionarios(FuncionarioController funcionarioController) {
        try {
            List<Funcionario> funcionarios = funcionarioController.obtenerFuncionarios();
            if (funcionarios.isEmpty()) {
                System.out.println("No hay funcionarios");
            } else {
                funcionarios.forEach(func -> {
                    System.out.println(" El ID del funcionario es: " + func.getIdFuncionario());
                    System.out.println(" El tipo de documento es: " + func.getTipoId());
                    System.out.println(" Nombre: " + func.getNombres());
                    System.out.println(" Apellidos: " + func.getApellidos());
                    System.out.println(" Sexo>: " + func.getSexo());
                    System.out.println(" La dirección es: " + func.getDireccion());
                    System.out.println(" El teléfono es: " + func.getTelefono());
                    System.out.println(" La fecha de nacimiento es: " + func.getFechaNacimiento());
                    System.out.println(" Su estado civil es: " + func.getEstadoCivil());
                    System.out.println("-----------");

                });

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void obtenerFuncionario(FuncionarioController funcionarioController) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite el ID del funcionario que desea consultar: ");
            int id = sc.nextInt();

            Funcionario func = funcionarioController.obtenerFuncionario(id);
            System.out.println(func);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void actualizarFuncionario(FuncionarioController funcionarioController) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite el ID del fucionario: ");
            int id = sc.nextInt();
            Funcionario funcExists = funcionarioController.obtenerFuncionario(id);
            if (funcExists == null) {
                System.out.println("El funcionario no existe en la base de datos");
                return;
            }
            System.out.println("id del empleado");
            int idFuncionario = sc.nextInt();
            System.out.println("Tipo id del empleado: ");
            String tipoid = sc.nextLine();
            System.out.println("Nombre del empleado: ");
            String nombre = sc.nextLine();
            System.out.println("Apellidos del empleado: ");
            String apellidos = sc.nextLine();
            System.out.println("Sexo del empleado: ");
            String sexo = sc.nextLine();
            System.out.println("Dirección del empleado: ");
            String direccion = sc.nextLine();
            System.out.println("Teléfono del empleado: ");
            int telefono = sc.nextInt();
           // System.out.println("Ingrese la fecha de nacimiento del empleado: (aaa/mm/dd)");
            //Date date = Date.(sc.nextLine());
            System.out.println("Estado civil: ");
            String estadocivil = sc.nextLine();

            Funcionario func = new Funcionario();
            func.setIdFuncionario(idFuncionario);
            func.setTipoId(tipoid);
            func.setNombres(nombre);
            func.setApellidos(apellidos);
            func.setSexo(sexo);
            func.setDireccion(direccion);
            func.setTelefono(telefono);
            //func.setFechaNacimiento();
            func.setEstadoCivil(estadocivil);
            funcionarioController.actualizarFuncionario(id, func);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void eliminarFuncionario(FuncionarioController funcionarioController){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese el ID del funcionario que desea eliminar: ");
            int id = sc.nextInt();

            Funcionario func = funcionarioController.obtenerFuncionario(id);
            if(func == null){
                System.out.println("El funcionario no existe.");

            } else{
                funcionarioController.eliminarFuncionario(id);
                System.out.println("Funcionario eliminado existosamente.");

            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FuncionarioController funcionarioController = new FuncionarioController();
        //crear(funcionarioController);
        //obtenerFuncionarios(funcionarioController);
         //obtenerFuncionario(funcionarioController);
        //actualizarFuncionario(funcionarioController);
         eliminarFuncionario(funcionarioController);

    }
}