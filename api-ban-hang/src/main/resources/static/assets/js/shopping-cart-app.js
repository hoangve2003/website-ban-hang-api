const app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function ($scope, $http) {
    // Quản lý giỏ hàng
    $scope.cart = {
        items: [],
        add(id) {
            var item = this.items.find(item => item.id == id)
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        },
        remove(id) {
            var index = this.items.findIndex(item => item.id == id)
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },
        amt_of(item) {
        },
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0)
        },
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0)
        },
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        loadToLocalStorage() {
            var json = localStorage.getItem("cart")
            this.items = json ? JSON.parse(json) : [];
        },
    }
    $scope.cart.loadToLocalStorage();

    $scope.order = {
        createDate: new Date(),
        address: "",
        account: {username: $("#username").text()},

        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: {id: item.id},
                    price: item.price,
                    quantity: item.qty
                }
            })
        },
        purchase() {
            const order = angular.copy(this);
            $http.post("/rest/orders", order).then(resp => {
                alert("dat hang thanh cong")
                $scope.cart.clear()
                location.href = "/order/detail/" + resp.data.id;
            }).catch(error => {
                alert("Loi")
                console.log(error)
            })
        }
    }
})