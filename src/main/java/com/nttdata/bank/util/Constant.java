package com.nttdata.bank.util;

public class Constant {
    public enum TypeAccount {
        Ahorro("62f30725c6b74a60b43029a4"),
        Corriente("62f30741c6b74a60b43029a5"),
        PlazoFijo("62f3078bc6b74a60b43029a6"),
        Personal("62f30802c6b74a60b43029a7"),
        Empresarial("62f3080dc6b74a60b43029a8"),
        TarjetaCredito("62f30825c6b74a60b43029a9");

        private String id;

        private TypeAccount(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }
    }

    public enum TypePerson {
        Personal("P"),
        Empresarial("E");

        private String id;

        private TypePerson(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }
    }
}
