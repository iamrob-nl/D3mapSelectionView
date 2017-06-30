/*global logger*/
/*
    D3mapSelectionView
    ========================

    @file      : D3mapSelectionView.js
    @version   : 1.0.0
    @author    : Rob Duits
    @date      : 3/14/2017
    @copyright : Incentro
    @license   : Apache 2

    Documentation
    ========================
    Describe your widget here.
*/

// Required module list. Remove unnecessary modules, you can always get them back from the boilerplate.
define([
    "dojo/_base/declare",
    "mxui/widget/_WidgetBase",
    "dijit/_TemplatedMixin",

    "mxui/dom",
    "dojo/dom",
    "dojo/dom-prop",
    "dojo/dom-geometry",
    "dojo/dom-class",
    "dojo/dom-style",
    "dojo/dom-construct",
    "dojo/_base/array",
    "dojo/_base/lang",
    "dojo/text",
    "dojo/html",
    "dojo/_base/event",

    "D3mapSelectionView/lib/jquery-1.11.2",
    "dojo/text!D3mapSelectionView/widget/template/D3mapSelectionView.html",
    "dojo/text!D3mapSelectionView/widget/data/world_countries.json",
    "D3mapSelectionView/lib/d3",
    "D3mapSelectionView/lib/d3-queue",
    "D3mapSelectionView/lib/d3-topojson",
], function (declare, _WidgetBase, _TemplatedMixin, dom, dojoDom, dojoProp, dojoGeometry, dojoClass, dojoStyle, dojoConstruct, dojoArray, lang, dojoText, dojoHtml, dojoEvent, _jQuery, widgetTemplate, D3Map, d3, queue, topojson){
  "use strict";

  var $ = _jQuery.noConflict(true);

  var format = d3.format(",");
  var active = d3.select(null);

  var margin = {top: 0, right: 0, bottom: 0, left: 200},
    width = window.innerWidth - margin.left - margin.right,
    height = 600 - margin.top - margin.bottom
  ;

  var projection = d3.geoMercator()
    .scale(130)
    .translate( [width / 2, height / 1.5])
  ;
  var path = d3.geoPath().projection(projection);

  // Declare widget's prototype.
  return declare("D3mapSelectionView.widget.D3mapSelectionView", [ _WidgetBase, _TemplatedMixin ], {
    // _TemplatedMixin will create our dom node using this HTML template.
    templateString: widgetTemplate,
    D3Map: D3Map,

    // DOM elements
    svg: null,

    // Parameters configured in the Modeler.
    mfToExecute: "",
    colorArray:"",
    thresholdArray: "",

    // Internal variables. Non-primitives created in the prototype are shared between all widget instances.
    _handles: null,
    _contextObj: null,
    _alertDiv: null,

    // dojo.declare.constructor is called to construct the widget instance. Implement to initialize non-primitive properties.
    constructor: function () {
      logger.debug(this.id + ".constructor");
      this._handles = [];
    },

    // dijit._WidgetBase.postCreate is called after constructing the widget. Implement to do extra setup work.
    postCreate: function () {
      logger.debug(this.id + ".postCreate");

      this._updateRendering();
      this._setupEvents();
    },

    // mxui.widget._WidgetBase.update is called when context is changed or initialized. Implement to re-render and / or fetch data.
    update: function (obj, callback) {
      logger.debug(this.id + ".update");

      this._contextObj = obj;
      this._resetSubscriptions();
      this._updateRendering(callback); // We're passing the callback to updateRendering to be called after DOM-manipulation
    },

    // mxui.widget._WidgetBase.enable is called when the widget should enable editing. Implement to enable editing if widget is input widget.
    enable: function () {
      logger.debug(this.id + ".enable");
    },

    // mxui.widget._WidgetBase.enable is called when the widget should disable editing. Implement to disable editing if widget is input widget.
    disable: function () {
      logger.debug(this.id + ".disable");
    },

    // mxui.widget._WidgetBase.resize is called when the page's layout is recalculated. Implement to do sizing calculations. Prefer using CSS instead.
    resize: function (box) {
      logger.debug(this.id + ".resize");
    },

    // mxui.widget._WidgetBase.uninitialize is called when the widget is destroyed. Implement to do special tear-down work.
    uninitialize: function () {
      logger.debug(this.id + ".uninitialize");
      // Clean up listeners, helper objects, etc. There is no need to remove listeners added with this.connect / this.subscribe / this.own.
    },

    // Attach events to HTML dom elements
    _setupEvents: function () {
      logger.debug(this.id + "._setupEvents");

      if(this._contextObj){
        this._contextObj.set(this.colorArray);
        this._contextObj.set(this.thresholdArray);
      }
    },

    _execMf: function (mf, guid, cb) {
      logger.debug(this.id + "._execMf");
      if (mf && guid) {
        mx.ui.action(mf, {
          params: {
            applyto: "selection",
            guids: [guid]
          },
          callback: lang.hitch(this, function (objs) {
            if (cb && typeof cb === "function") {
              cb(objs);
            }
          }),
          error: function (error) {
            console.debug(error.description);
          }
        }, this);
      }
    },

    // Rerender the interface.
    _updateRendering: function (callback) {
      logger.debug(this.id + "._updateRendering");

      // draw and attach event handlers
      if (this._contextObj !== null && this.svg !== null) {
        dojoStyle.set(this.domNode, "display", "block");
  			// this.loadData();
        this.drawObjectD3();
      } else {
        dojoStyle.set(this.domNode, "display", "none");
        // only redraw
        this.reDrawObjectD3();
        // this.restartSimulationD3();
      }

      // Important to clear all validations!
      this._clearValidations();

      // The callback, coming from update, needs to be executed, to let the page know it finished rendering
      this._executeCallback(callback, "_updateRendering");
    },

    // Handle validations.
    _handleValidation: function (validations) {
      logger.debug(this.id + "._handleValidation");
      this._clearValidations();
    },

    // Clear validations.
    _clearValidations: function () {
      logger.debug(this.id + "._clearValidations");
      dojoConstruct.destroy(this._alertDiv);
      this._alertDiv = null;
    },

    // Show an error message.
    _showError: function (message) {
      logger.debug(this.id + "._showError");
      if (this._alertDiv !== null) {
          dojoHtml.set(this._alertDiv, message);
          return true;
      }
      this._alertDiv = dojoConstruct.create("div", {
          "class": "alert alert-danger",
          "innerHTML": message
      });
      dojoConstruct.place(this._alertDiv, this.domNode);
    },

    // Add a validation.
    _addValidation: function (message) {
        logger.debug(this.id + "._addValidation");
        this._showError(message);
    },

    // Reset subscriptions.
    _resetSubscriptions: function () {
        logger.debug(this.id + "._resetSubscriptions");
        // Release handles on previous object, if any.
        this.unsubscribeAll();

        // When a mendix object exists create subscribtions.
        if (this._contextObj) {
            this.subscribe({
                guid: this._contextObj.getGuid(),
                callback: lang.hitch(this, function (guid) {
                    this._updateRendering();
                })
            });

            this.subscribe({
                guid: this._contextObj.getGuid(),
                attr: this.backgroundColor,
                callback: lang.hitch(this, function (guid, attr, attrValue) {
                    this._updateRendering();
                })
            });

            this.subscribe({
                guid: this._contextObj.getGuid(),
                val: true,
                callback: lang.hitch(this, this._handleValidation)
            });
        }
    },

    _executeCallback: function (cb, from) {
        logger.debug(this.id + "._executeCallback" + (from ? " from " + from : ""));
        if (cb && typeof cb === "function") {
            cb();
        }
    },


    // -------------------------------------------------------------------------
    drawObjectD3: function() {
      var widgetID = "#" + this.id;
      var svg = d3.select(this.svg)
        .attr("class", "map-container")
        .attr("width", width)
        .attr("height", height)
        .append("g")
        .attr("class", "map")
      ;

      // var zoom = d3.zoom()
      //   .scaleExtent( [1, 8] )
      //   .on("zoom", zoomed)
      // ;


      // Call a microflow
      var _this = this;
      mx.data.action({
        params: {
          actionname: this.mfToExecute
          // "D3.ACT_Orders_JSON"
        },
        origin: this.mxform,
        callback: function(obj) {
          var json = JSON.parse(obj);
          var worldMap = JSON.parse(D3Map);

          if(json) {
            lang.hitch(_this, _this.microflowJSON(json, worldMap));
          } else {
            alert("map has no data");
          }
        },
        error: function(error) {
          console.log(error.description);
        },
      });
    },


    // -------------------------------------------------------------------------
    microflowJSON: function(orders, data) {
      var svg = d3.select(this.svg);
      var ordersById = {};
      var itemsById = {};
      var _this = this;
      var colorArray = this.colorArray.split(",");
      var thresholdArray = this.thresholdArray.split(",").map(Number);
      var color = d3.scaleThreshold()
        .domain(thresholdArray)
        .range(colorArray)
      ;

      orders.forEach(function(d){
        if (d.item) {
          ordersById[d.id] = + d.item.length;
        } else {
          ordersById[d.id] = + 0;
        }
        itemsById[d.id] = d.item;
      });

      data.features.forEach(function(d) {
        d.orders = ordersById[d.id];
        d.items = itemsById;
      });

      svg.append("g")
        .attr("class", "countries")
        .selectAll("path")
        .data(data.features)
        .enter().append("path")
        .attr("d", path)
        .style("fill", function(d) {
          if(ordersById[d.id]) {
            return color(ordersById[d.id]);
          } else {
            return colorArray[0];
          }
        })
        .style("stroke", "white")
        .style("stroke-width", 1.5)
        .style("opacity", 0.8)
        .style("stroke", "white")
        .style("stroke-width", 0.3)
        .on("mousemove", _this.mousemove)
        .on("mouseover", _this.mouseover)
        .on("mouseout", _this.mouseout)
        .on("click", _this.clicked)
      ;
      // svg.call(zoom); // delete this line to disable free zooming
    },


    // -------------------------------------------------------------------------
    reDrawObjectD3: function() {
    },


    // -------------------------------------------------------------------------
    mousemove: function() {
      var cursor = [0, 0];
      cursor = d3.mouse(this);
      var cursorX = cursor[0];
      var cursorY = cursor[1];

      d3.select(".d3-tooltip")
        .style("transform", "translate(25px, 100px)")
        .style("left", cursorX + "px")
        .style("top", cursorY + "px")
      ;
    },


    // -------------------------------------------------------------------------
    mouseover: function(d) {
      var cursor = [0, 0];
      cursor = d3.mouse(this);
      var cursorX = cursor[0];
      var cursorY = cursor[1];
      var detailElement = d3.select("div.modal-details");

      if (detailElement.attr("visibility") !== "visible") {
        var countryName = d.properties.name;
        var amountOrders;
        if ( d.orders && !isNaN(d.orders) ) {
          amountOrders = format(d.orders);
        } else {
          amountOrders = 0;
        }

        // Set tooltips
        var tooltip = d3.select(".d3-tooltip");
        tooltip.html("");
        tooltip.html(function() {
          return "<p>" + countryName + "</p>";
        })
          .transition()
          .duration(300)
          .style("opacity", 1)
          .attr("visibility", "visible")
          .style("pointer-events", "all")
        ;
      }

      d3.select(this)
        .style("opacity", 1)
        .style("stroke", "white")
        .style("stroke-width", 1)
      ;
    },


    // -------------------------------------------------------------------------
    mouseout: function(d) {
      var tooltip = d3.select(".d3-tooltip");

      tooltip.transition()
        .duration(300)
        .style("opacity", 0)
        .attr("visibility", "hidden")
        .style("pointer-events", "none")
      ;

      d3.select(this)
        .style("opacity", 0.8)
        .style("stroke","white")
        .style("stroke-width",0.3)
      ;
    },


    // -------------------------------------------------------------------------
    clicked: function(d) {
      var svg = d3.select(this.svg);
      var cursor = [0, 0];
      cursor = d3.mouse(this);
      var cursorX = cursor[0];
      var cursorY = cursor[1];
      var detailElement = d3.select("div.modal-details");
      var amountOrders = format(d.orders);
      var name = d.properties.name;
      var item = d.items[d.id];

      // Click on an already selected country
      if (active.node() === this) {
        d3.select("div.modal-details")
          .transition()
          .duration(300)
          .style("opacity", 0)
          .style("pointer-events", "none")
          .attr("visibility", "hidden")
        ;

        active.classed("active", false);
        active = d3.select(null);
        d3.select(".countries").transition()
          .duration(750)
          .attr("transform", "translate(0, 0) scale(1)")
        ;
      } else {

        // select a country
        active.classed("active", false);
        active = d3.select(this).classed("active", true);
        detailElement.html("");
        if (isNaN(d.orders)) amountOrders = 0;
        detailElement.html(function() {
          return "<h1>" + name + "</h1>" +
          "<strong>Orders: </strong><span class='details'>" + amountOrders +"</span>";
        })
          .transition()
          .duration(750)
          .style("opacity", 1)
          .attr("visibility", "visible")
          .style("pointer-events", "all")
          .style("transform", "translate(25px, 100px)")
          .style("left", cursorX + "px")
          .style("top", cursorY + "px")
        ;

        if(item) {
          detailElement.append("ol");
          item.forEach(function(d) {
            d3.select("div.modal-details ol").append("li")
              .html("<strong>"+ d.number +"</strong>: <span class='details'>" + d.status + "</span>")
            ;
          });
        }

        d3.select(".d3-tooltip")
          .transition()
          .duration(300)
          .style("opacity", 0)
          .attr("visibility", "hidden")
          .style("pointer-events", "none")
        ;

        var bounds = path.bounds(d),
          dx = bounds[1][0] - bounds[0][0],
          dy = bounds[1][1] - bounds[0][1],
          x = (bounds[0][0] + bounds[1][0]) / 2,
          y = (bounds[0][1] + bounds[1][1]) / 2,
          scale = Math.max(1, Math.min(8, 0.9 / Math.max(dx / width, dy / height))),
          translate = [width / 2 - scale * x, height / 2 - scale * y]
        ;

        d3.select(".countries").transition()
          .duration(750)
          .attr("transform", "translate(" + translate + ") scale(" + scale + ")")
        ;
      }
    }
  });
});

require(["D3mapSelectionView/widget/D3mapSelectionView"]);
