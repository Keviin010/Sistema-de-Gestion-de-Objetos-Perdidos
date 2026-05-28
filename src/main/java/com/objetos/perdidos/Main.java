package com.objetos.perdidos;

import com.objetos.perdidos.dao.*;
import com.objetos.perdidos.model.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ObjetoDAO objetoDAO = new ObjetoDAO();
        PersonaDAO personaDAO = new PersonaDAO();
        ReclamoDAO reclamoDAO = new ReclamoDAO();
        UbicacionDAO ubicacionDAO = new UbicacionDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        int opcion;

        do {

            System.out.println("\n=== SISTEMA DE OBJETOS PERDIDOS ===");
            System.out.println("1. Gestion de Objetos");
            System.out.println("2. Gestion de Personas");
            System.out.println("3. Gestion de Reclamos");
            System.out.println("4. Ver Ubicaciones");
            System.out.println("5. Ver Categorias");
            System.out.println("6. Salir");

            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    menuObjetos(sc, objetoDAO, ubicacionDAO, categoriaDAO);
                    break;

                case 2:
                    menuPersonas(sc, personaDAO);
                    break;

                case 3:
                    menuReclamos(sc, reclamoDAO);
                    break;

                case 4:
                    System.out.println("\n--- UBICACIONES ---");
                    ubicacionDAO.listar();
                    break;

                case 5:
                    System.out.println("\n--- CATEGORIAS ---");
                    categoriaDAO.listar();
                    break;

                case 6:
                    System.out.println("Sistema finalizado");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 6);
    }

    private static void menuObjetos(Scanner sc, ObjetoDAO dao, UbicacionDAO ubicacionDAO, CategoriaDAO categoriaDAO) {

        int opcion;

        do {

            System.out.println("\n--- MENU OBJETOS ---");
            System.out.println("1. Listar todos los objetos");
            System.out.println("2. Listar objetos disponibles");
            System.out.println("3. Insertar objeto");
            System.out.println("4. Actualizar estado");
            System.out.println("5. Eliminar objeto");
            System.out.println("6. Volver");

            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    dao.listar();
                    break;

                case 2:
                    dao.listarDisponibles();
                    break;

                case 3:
                    System.out.println("\n--- CATEGORIAS ---");
                    categoriaDAO.listar();
                    System.out.println("\n--- UBICACIONES ---");
                    ubicacionDAO.listar();

                    Objeto o = new Objeto();

                    System.out.print("\nDescripcion: ");
                    o.descripcion = sc.nextLine();

                    System.out.print("Categoria: ");
                    String nombreCat = sc.nextLine();
                    o.categoriaId = categoriaDAO.buscarIdPorNombre(nombreCat);

                    if (o.categoriaId == -1) {
                        System.out.println("Categoria no encontrada");
                        break;
                    }

                    System.out.print("Ubicacion: ");
                    String nombreUbic = sc.nextLine();
                    o.ubicacionId = ubicacionDAO.buscarIdPorNombre(nombreUbic);

                    if (o.ubicacionId == -1) {
                        System.out.println("Ubicacion no encontrada");
                        break;
                    }

                    System.out.print("Fecha encontrado (YYYY-MM-DD): ");
                    o.fechaEncontrado = sc.nextLine();

                    System.out.print("Estado (Disponible/Reclamado): ");
                    o.estado = sc.nextLine();

                    dao.insertar(o);
                    System.out.println("Objeto insertado");
                    System.out.println("\n--- OBJETOS ACTUALIZADOS ---");
                    dao.listar();
                    break;

                case 4:
                    dao.listar();
                    System.out.print("ID del objeto: ");
                    int idActObj = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nuevo estado (Disponible/Reclamado): ");
                    String estado = sc.nextLine();

                    dao.actualizar(idActObj, estado);
                    System.out.println("Objeto actualizado");
                    System.out.println("\n--- OBJETOS ACTUALIZADOS ---");
                    dao.listar();
                    break;

                case 5:
                    dao.listar();
                    System.out.print("ID del objeto a eliminar: ");
                    int idElimObj = sc.nextInt();

                    dao.eliminar(idElimObj);
                    System.out.println("Objeto eliminado");
                    System.out.println("\n--- OBJETOS ACTUALIZADOS ---");
                    dao.listar();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 6);
    }

    private static void menuPersonas(Scanner sc, PersonaDAO dao) {

        int opcion;

        do {

            System.out.println("\n--- MENU PERSONAS ---");
            System.out.println("1. Listar personas");
            System.out.println("2. Insertar persona");
            System.out.println("3. Actualizar persona");
            System.out.println("4. Eliminar persona");
            System.out.println("5. Volver");

            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    dao.listar();
                    break;

                case 2:
                    Persona p = new Persona();

                    System.out.print("Nombre: ");
                    p.nombre = sc.nextLine();

                    System.out.print("Telefono: ");
                    p.telefono = sc.nextLine();

                    System.out.print("Email: ");
                    p.email = sc.nextLine();

                    System.out.print("Tipo (ReportaEncontrado/Reclama): ");
                    p.tipoPersona = sc.nextLine();

                    dao.insertar(p);
                    System.out.println("Persona insertada");
                    System.out.println("\n--- PERSONAS ACTUALIZADAS ---");
                    dao.listar();
                    break;

                case 3:
                    dao.listar();
                    System.out.print("ID de la persona: ");
                    int idActPer = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nuevo telefono: ");
                    String telefono = sc.nextLine();

                    System.out.print("Nuevo email: ");
                    String email = sc.nextLine();

                    dao.actualizar(idActPer, telefono, email);
                    System.out.println("Persona actualizada");
                    System.out.println("\n--- PERSONAS ACTUALIZADAS ---");
                    dao.listar();
                    break;

                case 4:
                    dao.listar();
                    System.out.print("ID de la persona a eliminar: ");
                    int idElimPer = sc.nextInt();

                    dao.eliminar(idElimPer);
                    System.out.println("Persona eliminada");
                    System.out.println("\n--- PERSONAS ACTUALIZADAS ---");
                    dao.listar();
                    break;

                case 5:
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 5);
    }

    private static void menuReclamos(Scanner sc, ReclamoDAO dao) {

        int opcion;

        do {

            System.out.println("\n--- MENU RECLAMOS ---");
            System.out.println("1. Listar todos los reclamos");
            System.out.println("2. Listar reclamos pendientes");
            System.out.println("3. Insertar reclamo");
            System.out.println("4. Actualizar estado");
            System.out.println("5. Eliminar reclamo");
            System.out.println("6. Volver");

            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    dao.listar();
                    break;

                case 2:
                    dao.listarPendientes();
                    break;

                case 3:
                    System.out.println("\n--- OBJETOS DISPONIBLES ---");
                    new ObjetoDAO().listarDisponibles();

                    System.out.println("\n--- PERSONAS ---");
                    new PersonaDAO().listar();

                    Reclamo r = new Reclamo();

                    System.out.print("\nObjeto ID: ");
                    r.objetoId = sc.nextInt();

                    System.out.print("Persona ID: ");
                    r.personaId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Fecha reclamo (YYYY-MM-DD): ");
                    r.fechaReclamo = sc.nextLine();

                    System.out.print("Estado (Pendiente/Verificado/Rechazado): ");
                    r.estadoReclamo = sc.nextLine();

                    dao.insertar(r);
                    System.out.println("Reclamo insertado");
                    System.out.println("\n--- RECLAMOS ACTUALIZADOS ---");
                    dao.listar();
                    break;

                case 4:
                    dao.listar();
                    System.out.print("ID del reclamo: ");
                    int idActRec = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nuevo estado (Pendiente/Verificado/Rechazado): ");
                    String estado = sc.nextLine();

                    dao.actualizar(idActRec, estado);
                    System.out.println("Reclamo actualizado");
                    System.out.println("\n--- RECLAMOS ACTUALIZADOS ---");
                    dao.listar();
                    break;

                case 5:
                    dao.listar();
                    System.out.print("ID del reclamo a eliminar: ");
                    int idElimRec = sc.nextInt();

                    dao.eliminar(idElimRec);
                    System.out.println("Reclamo eliminado");
                    System.out.println("\n--- RECLAMOS ACTUALIZADOS ---");
                    dao.listar();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 6);
    }
}