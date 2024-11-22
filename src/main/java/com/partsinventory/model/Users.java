package com.partsinventory.model;


    public class Users {
        private int id;
        private String username;
        private String password; // This should be hashed in production
        private String role;

        // Constructor
        public Users(int id, String username,String role) {
            this.id = id;
            this.username = username;
           // this.password = password;
            this.role = role;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        // ToString method (optional, useful for debugging)
        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", role='" + role + '\'' +
                    '}';
        }
    }



