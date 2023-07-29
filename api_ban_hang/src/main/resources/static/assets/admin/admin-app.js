var app = angular.module("admin-app", ["ngRoute"])
app.config(function ($routeProvider) {
    $routeProvider
        .when("/product", {
            templateUrl: "/assets/admin/product/index.html",
            controller: "product-ctrl"
        }).when("/authorize", {
        templateUrl: "/assets/admin/authority/index.html",
        controller: "authority-ctrl"
    }).when("/unauthorized", {
        templateUrl: "/assets/admin/authority/unauthorized.html",
        controller: "authority-ctrl"
    })
        .otherwise({
            template: "<footer class='d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top'>\n" +
                "        <div class='col-md-4 d-flex align-items-center'>\n" +
                "            <span class='text-muted'>© 2021 Company, Inc</span>\n" +
                "        </div>\n" +
                "    </footer>"
        })
})