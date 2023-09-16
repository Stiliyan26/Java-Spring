package softuni.battle_ships_exam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.battle_ships_exam.domain.entities.Ship;
import softuni.battle_ships_exam.domain.helpers.LoggedUser;
import softuni.battle_ships_exam.domain.model.StatsShipModel;
import softuni.battle_ships_exam.domain.model.UserWithShipsModel;
import softuni.battle_ships_exam.domain.model.binding.BattleShipsModel;
import softuni.battle_ships_exam.repository.ShipRepository;
import softuni.battle_ships_exam.services.BattleService;
import softuni.battle_ships_exam.services.ShipService;
import softuni.battle_ships_exam.services.UserService;

import java.util.List;

@Controller
@RequestMapping
public class HomeController {
    private final LoggedUser loggedUser;
    private final BattleService battleService;
    private final UserService userService;
    private final ShipRepository shipRepository;
    private final ShipService shipService;


    @Autowired
    public HomeController(LoggedUser loggedUser,
                          BattleService battleService, UserService userService, ShipRepository shipRepository, ShipService shipService) {
        this.loggedUser = loggedUser;
        this.battleService = battleService;
        this.userService = userService;
        this.shipRepository = shipRepository;
        this.shipService = shipService;
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView modelAndView) {
        String userId = this.loggedUser.getId();

        UserWithShipsModel loggedUserWithShips = battleService.getUserWithShips(userId);
        UserWithShipsModel notLoggedUserWithShips = battleService.getUserWithShips(this.userService
                .findByIdNot(userId)
                .getId());

        modelAndView.setViewName("home");
        modelAndView.addObject("loggedUserWithShips", loggedUserWithShips);
        modelAndView.addObject("notLoggedUserWithShips", notLoggedUserWithShips);

        return modelAndView;
    }

    @PostMapping("/battle")
    public String getHome(
            @ModelAttribute(name = "battleShipsModel") BattleShipsModel battleShipsModel) {
        this.shipService.fight(battleShipsModel);

        return "redirect:home";
    }

    @GetMapping
    public String getIndex() {
        return "index";
    }

    @ModelAttribute(name = "battleShipsModel")
    public BattleShipsModel battleShipsModel() {
        return new BattleShipsModel();
    }

    @ModelAttribute(name = "allShips")
    public List<StatsShipModel> ships() {
        return this.shipService.sortShipsByStats();
    }
}
