package com.example.aasims.smarthealth.model;

public class Doctor {

          String doctorId;
          String name;
          String email;
          String password;
          String mob;
          String address;
          String type;


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(String id) {
            this.doctorId = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    }

