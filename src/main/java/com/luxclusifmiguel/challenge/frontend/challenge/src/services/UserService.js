import axios from "axios";

const USERS_API = "http://localhost:8080/api/user";

class UserService {

    getUsers() {
        return axios.get(USERS_API + "/");
    }

    createUser(user) {
        return axios.post(USERS_API + "/", user);
    }

    getUserById(userId) {
        return axios.get(USERS_API + "/" + userId);
    }

    deleteUser(userId) {
        return axios.delete(USERS_API + "/" + userId);
    }
}

export default new UserService();