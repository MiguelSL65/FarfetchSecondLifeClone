import axios from "axios";

const PRODUCT_API = "http://localhost:8080/api/product";

class ProductService {

    getUserProducts(userID) {
        return axios.get(PRODUCT_API + "/" + userID + "/product");
    }

    getUserSpecificProduct(userId, productId) {
        return axios.get(PRODUCT_API + "/" + userId + "/product/" + productId);
    }

    createProduct(product, userId) {
        return axios.post(PRODUCT_API + "/" + userId + "/product", product);
    }

    removeProduct(userId, productId) {
        return axios.delete(PRODUCT_API + "/" + userId + "/products/" + productId + "/remove");
    }
}

export default new ProductService();