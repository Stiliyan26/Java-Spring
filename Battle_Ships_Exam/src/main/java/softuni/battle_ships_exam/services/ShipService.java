package softuni.battle_ships_exam.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.battle_ships_exam.domain.entities.Ship;
import softuni.battle_ships_exam.domain.helpers.LoggedUser;
import softuni.battle_ships_exam.domain.model.CategoryModel;
import softuni.battle_ships_exam.domain.model.ShipModel;
import softuni.battle_ships_exam.domain.model.StatsShipModel;
import softuni.battle_ships_exam.domain.model.UserModel;
import softuni.battle_ships_exam.domain.model.binding.BattleShipsModel;
import softuni.battle_ships_exam.domain.model.binding.ShipAddModel;
import softuni.battle_ships_exam.repository.ShipRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipService {
    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ShipService(
            ShipRepository shipRepository,
            ModelMapper modelMapper,
            LoggedUser loggedUser,
            UserService userService,
            CategoryService categoryService) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public void addShip(ShipAddModel shipAddModel) {
        UserModel userModel = this.userService
                .findById(this.loggedUser.getId());

        CategoryModel categoryModel = this.categoryService
                .findByName(shipAddModel.getCategory());

        Ship shipToSave = this.modelMapper.map(ShipModel.builder()
                .category(categoryModel)
                .created(shipAddModel.getCreated())
                .name(shipAddModel.getName())
                .health(shipAddModel.getHealth())
                .power(shipAddModel.getPower())
                .user(userModel)
                .build(), Ship.class);

        this.shipRepository.saveAndFlush(shipToSave);
    }

    public List<ShipModel> findAllByUserId(String id) {
        return this.shipRepository.findAllByUserId(id)
                .orElseThrow()
                .stream()
                .map(ship -> this.modelMapper
                        .map(ship, ShipModel.class))
                .toList();
    }

    public void fight(BattleShipsModel battleShipsModel) {
        final String loggedUserShipId = battleShipsModel.getLoggedUserShip();
        final String notLoggedUserShipId = battleShipsModel.getNotLoggedUserShip();

        final Ship loggedShips = this.shipRepository.findById(loggedUserShipId)
                .orElseThrow();

        final Ship notLoggedShips = this.shipRepository.findById(notLoggedUserShipId)
                .orElseThrow();

        notLoggedShips.setHealth(notLoggedShips.getHealth() - loggedShips.getPower());

        if (notLoggedShips.getHealth() <= 0 ) {
            this.shipRepository.deleteById(notLoggedUserShipId);
        } else {
            this.shipRepository.saveAndFlush(notLoggedShips);
        }
    }

    public List<StatsShipModel> sortShipsByStats() {
        return this.shipRepository.findAll()
                .stream()
                .sorted(Comparator
                        .comparing(Ship::getName)
                        .thenComparing(Ship::getHealth)
                        .thenComparing(Ship::getPower))
                .map(ship -> this.modelMapper
                        .map(ship, StatsShipModel.class))
                .collect(Collectors.toList());
    }
}
