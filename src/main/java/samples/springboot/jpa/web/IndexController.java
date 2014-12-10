package samples.springboot.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import samples.springboot.jpa.domain.Note;
import samples.springboot.jpa.repository.NoteRepository;

import java.util.List;

/**
 * Created by izeye on 14. 12. 10..
 */
@Controller
public class IndexController {

  @Autowired
  private NoteRepository noteRepository;

  @RequestMapping("/")
  @Transactional(readOnly = true)
  public ModelAndView index() {
    List<Note> notes = noteRepository.findAll();
    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("notes", notes);
    return modelAndView;
  }

}
