package softuni.battle_ships_exam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.battle_ships_exam.domain.model.ShipModel;
import softuni.battle_ships_exam.domain.model.UserModel;
import softuni.battle_ships_exam.domain.model.UserWithShipsModel;

import java.util.List;

@Service
public class BattleService {
    private final UserService userService;
    private final ShipService shipService;

    @Autowired
    public BattleService(UserService userService,
                         ShipService shipService) {
        this.userService = userService;
        this.shipService = shipService;
    }

    public UserWithShipsModel getUserWithShips(String id) {
        UserModel userModel = this.userService
                .findById(id);

        List<ShipModel> allShipsByUserId = this.shipService
                .findAllByUserId(id);

        return UserWithShipsModel.builder()
                .userModel(userModel)
                .shipModelList(allShipsByUserId)
                .build();
    }
}
