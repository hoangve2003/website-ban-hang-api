app.controller("authority-ctrl", function ($scope, $http, $location) {
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];

    $scope.initialize = function () {
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        });

        $http.get("/rest/accounts?admin=true").then(resp => {
            $scope.admins = resp.data;
        });

        $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            $location.path("/unauthorized");
        });
    };

    $scope.authority_of = function (acc, role) {
        console.log($scope.authorities); // Kiểm tra dữ liệu trong $scope.authorities
        if ($scope.authorities && Array.isArray($scope.authorities)) {
            return $scope.authorities.find(ur =>
                ur.account && ur.account.username === acc.username && ur.role.id === role.id
            );
        }
        return undefined;
    };


    $scope.authority_changed = function (acc, role) {
        var authority = $scope.authority_of(acc, role);
        if (authority) {
            $scope.revoke_authority(authority);
        } else {
            authority = { account: acc, role: role };
            $scope.grant_authority(authority);
        }
    };

    $scope.grant_authority = function (authority) {
        if (!Array.isArray($scope.authorities)) {
            // Nếu $scope.authorities không tồn tại hoặc không phải là mảng, gán nó thành một mảng trống
            $scope.authorities = [];
        }

        $http.post(`/rest/authorities`, authority).then(resp => {
            $scope.authorities.push(resp.data);
            alert("Cấp quyền thành công");
        }).catch(error => {
            alert("Cấp quyền thất bại");
            console.log(error);
        });
    };


    $scope.revoke_authority = function (authority) {
        $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
            var index = $scope.authorities.findIndex(a => a.id === authority.id);
            if (index !== -1) {
                $scope.authorities.splice(index, 1);
            }
            alert("Thu hồi quyền thành công");
        }).catch(error => {
            alert("Thu hồi quyền thất bại");
            console.log(error);
        });
    };

    $scope.initialize();
});
