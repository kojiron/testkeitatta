package controllers

import play.api.mvc.Controller
import play.api.mvc.Action

/**
 * @author ooshima
 */
class IndexController extends Controller{
  
  def index() = Action {
    Ok(views.html.index())
  }
}