'use strict';
/**
 * @ngdoc overview
 * @name sbAdminApp
 * @description
 * # sbAdminApp
 *
 * Main module of the application.
 */
angular
  .module( APP_NAME, [
    'oc.lazyLoad',
    'ui.router',
	'smart-table',
    'ui.bootstrap',
    'angular-loading-bar'
  ])
  .config(['$stateProvider','$urlRouterProvider','$ocLazyLoadProvider',function ($stateProvider,$urlRouterProvider,$ocLazyLoadProvider) {
    
    $ocLazyLoadProvider.config({
      debug:false,
      events:true,
    });

    $urlRouterProvider.otherwise('/dashboard/home');

    $stateProvider
      .state('dashboard', {
        url:'/dashboard',
        templateUrl: 'views/dashboard/main.html',
        resolve: {
            loadMyDirectives:function($ocLazyLoad){
                return $ocLazyLoad.load(
                {
                    name:APP_NAME,
                    files:[
                    'scripts/directives/header/header.js',
                    'scripts/directives/header/header-notification/header-notification.js',
                    'scripts/directives/sidebar/sidebar.js',
                    'scripts/directives/sidebar/sidebar-search/sidebar-search.js',
                    'scripts/directives/dashboard/list-table/list-table.js',
                    'scripts/directives/alerts/alerts.js',
                    
                    'scripts/utils/Utils.js',
                    'scripts/model/AlertDTO.js',
                    'scripts/model/QualityDTO.js',
                    'scripts/model/ResolutionDTO.js',
                    'scripts/model/StateDTO.js',
                    'scripts/model/PopulationDTO.js',
                    'scripts/model/ConcessionaireDTO.js',
                    'scripts/model/ConcessionTypeDTO.js',
                    'scripts/model/ChannelDTO.js',
                    'scripts/model/PaginationAPI.js',
                    'scripts/model/FilterAPI.js',
                    'scripts/model/OrderAPI.js',
                    'scripts/model/QueryHelper.js',
                    'scripts/model/ValueDTO.js'
                    ]
                }),
                $ocLazyLoad.load(
                {
                   name:'toggle-switch',
                   files:["bower_components/angular-toggle-switch/angular-toggle-switch.min.js",
                          "bower_components/angular-toggle-switch/angular-toggle-switch.css"
                      ]
                }),
                $ocLazyLoad.load(
                {
                  name:'ngAnimate',
                  files:['bower_components/angular-animate/angular-animate.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngCookies',
                  files:['bower_components/angular-cookies/angular-cookies.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngResource',
                  files:['bower_components/angular-resource/angular-resource.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngSanitize',
                  files:['bower_components/angular-sanitize/angular-sanitize.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngTouch',
                  files:['bower_components/angular-touch/angular-touch.js']
                })
            }
        }
    })
      .state('dashboard.home',{
        url:'/home',
        controller: 'HomeController',
        templateUrl:'views/dashboard/home.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name: APP_NAME,
              files: [ 'scripts/controllers/HomeController.js',
		              'scripts/directives/timeline/timeline.js',
		              'scripts/directives/notifications/notifications.js',
		              'scripts/directives/dashboard/stats/stats.js',
		              'scripts/model/StatDTO.js']
            })
          }
        }
      })
      .state('dashboard.state',{
        url:'/estados',
        controller: 'StateController',
        templateUrl:'views/states.html',
        resolve: {
            loadMyFiles:function($ocLazyLoad) {
              return $ocLazyLoad.load({
                name:APP_NAME,
                files:['scripts/controllers/StateController.js']
              })
            }
          }
    })
      .state('dashboard.population',{
        url:'/poblaciones',
        controller: 'PopulationController',
        templateUrl:'views/populations.html',
        resolve: {
	        loadMyFiles:function($ocLazyLoad) {
	          return $ocLazyLoad.load({
	            name:APP_NAME,
	            files:['scripts/controllers/PopulationController.js']
	          })
	        }
	      }
    })		  
      .state('dashboard.concessionaire',{
        url:'/concesionarias',
        controller: 'ConcessionaireController',
        templateUrl:'views/concessionaires.html',
        resolve: {
            loadMyFiles:function($ocLazyLoad) {
              return $ocLazyLoad.load({
                name:APP_NAME,
                files:['scripts/controllers/ConcessionaireController.js']
              })
            }
          }
    })
	.state('dashboard.concessionType',{
	    url:'/concesiones',
	    controller: 'ConcessionTypeController',
	    templateUrl:'views/concession-types.html',
	    resolve: {
	        loadMyFiles:function($ocLazyLoad) {
	          return $ocLazyLoad.load({
	            name:APP_NAME,
	            files:['scripts/controllers/ConcessionTypeController.js']
	          })
	        }
	      }
    })
      .state('dashboard.channel',{
        url:'/canales',
        controller: 'ChannelController',
        templateUrl:'views/channels.html',
        resolve: {
            loadMyFiles:function($ocLazyLoad) {
              return $ocLazyLoad.load({
                name:APP_NAME,
                files:['scripts/controllers/ChannelController.js']
              })
            }
          }
      })
  }]);

    
