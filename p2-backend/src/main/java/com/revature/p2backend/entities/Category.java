package com.revature.p2backend.entities;


public enum Category {

    NECKLACES{
        @Override
        public String toString() {
            return "NECKLACES";
        }
    },

    RINGS{
        @Override
        public String toString() {
            return "RINGS";
        }
    },

    BRACELETS{
        @Override
        public String toString() {
            return "BRACELETS";
        }
    },

    EARRINGS{
        @Override
        public String toString() {
            return "EARRINGS";
        }
    },

    WATCHES{
        @Override
        public String toString() {return "WATCHES";}
        }


}
