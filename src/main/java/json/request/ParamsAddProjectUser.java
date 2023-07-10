package json.request;

public class ParamsAddProjectUser {
    private String projectID;
    private String userID;
    private String role;

    public static class Builder{
        private String projectID;
        private String userID;
        private String role;

        public Builder setProjectID(String projectID) {
            this.projectID = projectID;
            return this;
        }

        public Builder setUserID(String userID) {
            this.userID = userID;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public String[] build(){
            String[] param = new String[3];
            param[0] = projectID;
            param[1] = userID;
            if (role != null && !role.isEmpty()){
                param[2] = role;
            }
            else {
                param[2] = "";
            }

            return param;
        }
    }
}