package CAJERO;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Persona {

    public ArrayList<usuario> ListaUsuarios = new ArrayList<>();
    public usuario user1 = new usuario("1234", "brad", "velarde", "fabian", "112233445566778899", 128);
    public usuario user2 = new usuario("1436", "pepe", "quispe", "lozano", "998877665544332211", 50);

    public void Listado() {
        ListaUsuarios.add(user1);
        ListaUsuarios.add(user2);
    }

    public void Acceso() {
        int contador = 1;
        String contraIntento, nombreIntento, apellidoPIntento, apellidoMIntento;
        do {
            nombreIntento = JOptionPane.showInputDialog("Ingrese el Nombre:");
            apellidoPIntento = JOptionPane.showInputDialog("Ingrese el Apellido Paterno:");
            apellidoMIntento = JOptionPane.showInputDialog("Ingrese el Apellido Materno:");
            contraIntento = JOptionPane.showInputDialog("Ingrese la Clave Secreta:");

            for (int i = 0; i < ListaUsuarios.size(); i++) {
                if (nombreIntento.equals(ListaUsuarios.get(i).nombre) && 
                    apellidoPIntento.equals(ListaUsuarios.get(i).apellidoP) &&
                    apellidoMIntento.equals(ListaUsuarios.get(i).apellidoM) &&
                    contraIntento.equals(ListaUsuarios.get(i).clave) && contador < 4) {
                    contador = 5;
                    Opciones(i);
                }
            }

            contador++;

            if (contador < 4) {
                JOptionPane.showMessageDialog(null, "Datos incorrectos, Vuelva a Intentarlo");
            } else if (contador == 4) {
                JOptionPane.showMessageDialog(null, "DEMASIADOS INTENTOS, CERRANDO PROGRAMA");
            }

        } while (contador < 4);
    }

    public void Opciones(int usuario) {
        int opcion;
        opcion = Integer.parseInt(JOptionPane.showInputDialog("1.Deposito\n2.Retiro\n3.Consulta\n4.Salir"));
        switch (opcion) {
            case 1 ->
                Deposito(ListaUsuarios.get(usuario).numCuenta);
            case 2 ->
                Retiro(usuario);
            case 3 ->
                Consulta(usuario);
            case 4 ->
                Salir(usuario);
            default ->
                Error();
        }
    }

    public void Deposito(String cuenta) {
        int opcion;
        double monto;
        String otraCuenta;
        int cuentaActual = 0;
        for (int i = 0; i < ListaUsuarios.size(); i++) {
            if (cuenta == ListaUsuarios.get(i).numCuenta) {
                cuentaActual = i;
            }
        }
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "1.Deposito a tu misma cuenta\n2.Deposito a otra cuenta"));
        switch (opcion) {
            case 1 -> {
                monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a depositar:"));
                for (int i = 0; i < ListaUsuarios.size(); i++) {
                    if (cuenta == ListaUsuarios.get(i).numCuenta) {
                        ListaUsuarios.get(i).saldo += monto;
                        JOptionPane.showMessageDialog(null, ListaUsuarios.get(i).saldo);
                        Continuar(i);
                    }
                }
            }
            case 2 -> {
                otraCuenta = JOptionPane.showInputDialog("Ingrese el numero de cuenta");

                if (otraCuenta.equals(ListaUsuarios.get(cuentaActual).numCuenta)) {
                    JOptionPane.showMessageDialog(null, "No puede colocar su misma cuenta");
                    Continuar(cuentaActual);
                    break;
                }
                monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a depositar:"));
                for (int i = 0; i < ListaUsuarios.size(); i++) {
                    if (otraCuenta.equals(ListaUsuarios.get(i).numCuenta)) {
                        ListaUsuarios.get(i).saldo += monto;
                        JOptionPane.showMessageDialog(null, ListaUsuarios.get(i).saldo);
                        Continuar(cuentaActual);
                    }
                }
            }
        }
    }

    public void Continuar(int cuenta) {
        String[] opciones = {"Continuar", "Finalizar"};
        int seleccion = JOptionPane.showOptionDialog(null, "¿Desea continuar?", "Continuar", 0,
                JOptionPane.QUESTION_MESSAGE, null, opciones, null);
        if (seleccion == 0) {
            Opciones(cuenta);
        }
    }

    public void Retiro(int cuenta) {
        double montoRetirar;

        montoRetirar = Double.parseDouble(JOptionPane.showInputDialog("Saldo actual de la cuenta: " + ListaUsuarios.get(cuenta).saldo + "\nIngrese el monto a retirar:"));

        if (montoRetirar > ListaUsuarios.get(cuenta).saldo) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente, Retiro Denegado");
            Continuar(cuenta);
        } else {
            ListaUsuarios.get(cuenta).saldo -= montoRetirar;
            JOptionPane.showMessageDialog(null, "Retiro excitoso!\nSaldo actual de la cuenta: " + ListaUsuarios.get(cuenta).saldo);
            Continuar(cuenta);
        }

    }

    public void Consulta(int cuenta) {
        JOptionPane.showMessageDialog(null, "Saldo disponible en la cuenta es de: \n" + ListaUsuarios.get(cuenta).saldo);
        Continuar(cuenta);
    }

    public void Salir(int cuenta) {
        String[] opciones = {"Continuar", "Finalizar"};
        int seleccion = JOptionPane.showOptionDialog(null, "¿Desea Finalizar su sesion?", "Cerrando Sesion...", 0,
                JOptionPane.QUESTION_MESSAGE, null, opciones, null);
        
        if (seleccion == 0) {
            Opciones(cuenta);
        }else if (seleccion == 1){
            JOptionPane.showMessageDialog(null, "Finalizando Sesion...");
        }
    }

    public void Error() {
        JOptionPane.showMessageDialog(null, "----------------ERROR----------------\nVALOR INGRESADO NO VALIDO");
    }

    public static void main(String[] args) {
        Persona w = new Persona();
        w.Listado();
        w.Acceso();
    }
}
