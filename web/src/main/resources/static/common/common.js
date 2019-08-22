/*
/!*!
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 * 项目自定义的公共JavaScript，可覆盖jeesite.js里的方法
 *!/
/!*!
 * jQuery Validation Plugin v1.16.0
 *
 * http://jqueryvalidation.org/
 *
 * Copyright (c) 2016 Jörn Zaefferer
 * Released under the MIT license
 *!/
(function (a) {
    if (typeof define === "function" && define.amd) {
        define(["common"], a)
    } else {
        if (typeof module === "object" && module.exports) {
            module.exports = a(require("common"))
        } else {
            a(jQuery)
        }
    }
}(function (c) {
    c.extend(c.fn, {
        validate: function (d) {
            if (!this.length) {
                if (d && d.debug && window.console) {
                    console.warn("Nothing selected, can't validate, returning nothing.")
                }
                return
            }
            var e = c.data(this[0], "validator");
            if (e) {
                return e
            }
            this.attr("novalidate", "novalidate");
            e = new c.validator(d, this[0]);
            c.data(this[0], "validator", e);
            if (e.settings.onsubmit) {
                this.on("click.validate", ":submit", function (f) {
                    if (e.settings.submitHandler) {
                        e.submitButton = f.target
                    }
                    if (c(this).hasClass("cancel")) {
                        e.cancelSubmit = true
                    }
                    if (c(this).attr("formnovalidate") !== undefined) {
                        e.cancelSubmit = true
                    }
                });
                this.on("submit.validate", function (f) {
                    if (e.settings.debug) {
                        f.preventDefault()
                    }

                    function g() {
                        var i, h;
                        if (e.settings.submitHandler) {
                            if (e.submitButton) {
                                i = c("<input type='hidden'/>").attr("name", e.submitButton.name).val(c(e.submitButton).val()).appendTo(e.currentForm)
                            }
                            h = e.settings.submitHandler.call(e, e.currentForm, f);
                            if (e.submitButton) {
                                i.remove()
                            }
                            if (h !== undefined) {
                                return h
                            }
                            return false
                        }
                        return true
                    }

                    if (e.cancelSubmit) {
                        e.cancelSubmit = false;
                        return g()
                    }
                    if (e.form()) {
                        if (e.pendingRequest) {
                            e.formSubmitted = true;
                            return false
                        }
                        return g()
                    } else {
                        e.focusInvalid();
                        return false
                    }
                })
            }
            return e
        }, valid: function () {
            var e, d, f;
            if (c(this[0]).is("form")) {
                e = this.validate().form()
            } else {
                f = [];
                e = true;
                d = c(this[0].form).validate();
                this.each(function () {
                    e = d.element(this) && e;
                    if (!e) {
                        f = f.concat(d.errorList)
                    }
                });
                d.errorList = f
            }
            return e
        }, rules: function (g, d) {
            var i = this[0], f, k, l, h, e, j;
            if (i == null || i.form == null) {
                return
            }
            if (g) {
                f = c.data(i.form, "validator").settings;
                k = f.rules;
                l = c.validator.staticRules(i);
                switch (g) {
                    case"add":
                        c.extend(l, c.validator.normalizeRule(d));
                        delete l.messages;
                        k[i.name] = l;
                        if (d.messages) {
                            f.messages[i.name] = c.extend(f.messages[i.name], d.messages)
                        }
                        break;
                    case"remove":
                        if (!d) {
                            delete k[i.name];
                            return l
                        }
                        j = {};
                        c.each(d.split(/\s/), function (m, n) {
                            j[n] = l[n];
                            delete l[n];
                            if (n === "required") {
                                c(i).removeAttr("aria-required")
                            }
                        });
                        return j
                }
            }
            h = c.validator.normalizeRules(c.extend({}, c.validator.classRules(i), c.validator.attributeRules(i), c.validator.dataRules(i), c.validator.staticRules(i)), i);
            if (h.required) {
                e = h.required;
                delete h.required;
                h = c.extend({required: e}, h);
                c(i).attr("aria-required", "true")
            }
            if (h.remote) {
                e = h.remote;
                delete h.remote;
                h = c.extend(h, {remote: e})
            }
            return h
        }
    });
    c.extend(c.expr.pseudos || c.expr[":"], {
        blank: function (d) {
            return !c.trim("" + c(d).val())
        }, filled: function (d) {
            var e = c(d).val();
            return e !== null && !!c.trim("" + e)
        }, unchecked: function (d) {
            return !c(d).prop("checked")
        }
    });
    c.validator = function (d, e) {
        this.settings = c.extend(true, {}, c.validator.defaults, d);
        this.currentForm = e;
        this.init()
    };
    c.validator.format = function (d, e) {
        if (arguments.length === 1) {
            return function () {
                var f = c.makeArray(arguments);
                f.unshift(d);
                return c.validator.format.apply(this, f)
            }
        }
        if (e === undefined) {
            return d
        }
        if (arguments.length > 2 && e.constructor !== Array) {
            e = c.makeArray(arguments).slice(1)
        }
        if (e.constructor !== Array) {
            e = [e]
        }
        c.each(e, function (f, g) {
            d = d.replace(new RegExp("\\{" + f + "\\}", "g"), function () {
                return g
            })
        });
        return d
    };
    c.extend(c.validator, {
        defaults: {
            messages: {},
            groups: {},
            rules: {},
            errorClass: "error",
            pendingClass: "pending",
            validClass: "valid",
            errorElement: "label",
            focusCleanup: false,
            focusInvalid: true,
            errorContainer: c([]),
            errorLabelContainer: c([]),
            onsubmit: true,
            ignore: ":hidden",
            ignoreTitle: false,
            onfocusin: function (d) {
                this.lastActive = d;
                if (this.settings.focusCleanup) {
                    if (this.settings.unhighlight) {
                        this.settings.unhighlight.call(this, d, this.settings.errorClass, this.settings.validClass)
                    }
                    this.hideThese(this.errorsFor(d))
                }
            },
            onfocusout: function (d) {
                if (!this.checkable(d) && (d.name in this.submitted || !this.optional(d))) {
                    this.element(d)
                }
            },
            onkeyup: function (e, f) {
                var d = [16, 17, 18, 20, 35, 36, 37, 38, 39, 40, 45, 144, 225];
                if (f.which === 9 && this.elementValue(e) === "" || c.inArray(f.keyCode, d) !== -1) {
                    return
                } else {
                    if (e.name in this.submitted || e.name in this.invalid) {
                        this.element(e)
                    }
                }
            },
            onclick: function (d) {
                if (d.name in this.submitted) {
                    this.element(d)
                } else {
                    if (d.parentNode.name in this.submitted) {
                        this.element(d.parentNode)
                    }
                }
            },
            highlight: function (f, d, e) {
                if (f.type === "radio") {
                    this.findByName(f.name).addClass(d).removeClass(e)
                } else {
                    c(f).addClass(d).removeClass(e)
                }
            },
            unhighlight: function (f, d, e) {
                if (f.type === "radio") {
                    this.findByName(f.name).removeClass(d).addClass(e)
                } else {
                    c(f).removeClass(d).addClass(e)
                }
            }
        },
        setDefaults: function (d) {
            c.extend(c.validator.defaults, d)
        },
        messages: {
            required: "输入框不可为空!",
            remote: "Please fix this field.",
            Chinese: "请输入有效中文!",
            nae:"请输入有效数字或英文!",
            English:"请输入有效英文!",
            date: "请输入正确日期!格式:20190501",
            dateISO: "Please enter a valid date (ISO).",
            number: "请输入有效数值!",
            digits: "请输入有效数字!",
            equalTo: "Please enter the same value again.",
            maxlength: c.validator.format("Please enter no more than {0} characters."),
            minlength: c.validator.format("Please enter at least {0} characters."),
            rangelength: c.validator.format("Please enter a value between {0} and {1} characters long."),
            range: c.validator.format("Please enter a value between {0} and {1}."),
            max: c.validator.format("Please enter a value less than or equal to {0}."),
            min: c.validator.format("Please enter a value greater than or equal to {0}."),
            step: c.validator.format("Please enter a multiple of {0}.")
        },
        autoCreateRanges: false,
        prototype: {
            init: function () {
                this.labelContainer = c(this.settings.errorLabelContainer);
                this.errorContext = this.labelContainer.length && this.labelContainer || c(this.currentForm);
                this.containers = c(this.settings.errorContainer).add(this.settings.errorLabelContainer);
                this.submitted = {};
                this.valueCache = {};
                this.pendingRequest = 0;
                this.pending = {};
                this.invalid = {};
                this.reset();
                var d = (this.groups = {}), f;
                c.each(this.settings.groups, function (g, h) {
                    if (typeof h === "string") {
                        h = h.split(/\s/)
                    }
                    c.each(h, function (j, i) {
                        d[i] = g
                    })
                });
                f = this.settings.rules;
                c.each(f, function (g, h) {
                    f[g] = c.validator.normalizeRule(h)
                });

                function e(j) {
                    if (!this.form && this.hasAttribute("contenteditable")) {
                        this.form = c(this).closest("form")[0]
                    }
                    var h = c.data(this.form, "validator"), g = "on" + j.type.replace(/^validate/, ""), i = h.settings;
                    if (i[g] && !c(this).is(i.ignore)) {
                        i[g].call(h, this, j)
                    }
                }

                c(this.currentForm).on("focusin.validate focusout.validate keyup.validate", ":text, [type='password'], [type='file'], select, textarea, [type='number'], [type='search'], [type='tel'], [type='nae'], [type='English'],[type='Chinese'], [type='datetime'], [type='date'], [type='month'], [type='week'], [type='time'], [type='datetime-local'], [type='range'], [type='color'], [type='radio'], [type='checkbox'], [contenteditable], [type='button']", e).on("click.validate", "select, option, [type='radio'], [type='checkbox']", e);
                if (this.settings.invalidHandler) {
                    c(this.currentForm).on("invalid-form.validate", this.settings.invalidHandler)
                }
                c(this.currentForm).find("[required], [data-rule-required], .required").attr("aria-required", "true")
            }, form: function () {
                this.checkForm();
                c.extend(this.submitted, this.errorMap);
                this.invalid = c.extend({}, this.errorMap);
                if (!this.valid()) {
                    c(this.currentForm).triggerHandler("invalid-form", [this])
                }
                this.showErrors();
                return this.valid()
            }, checkForm: function () {
                this.prepareForm();
                for (var d = 0, e = (this.currentElements = this.elements()); e[d]; d++) {
                    this.check(e[d])
                }
                return this.valid()
            }, element: function (h) {
                var i = this.clean(h), g = this.validationTargetFor(i), f = this, d = true, e, j;
                if (g === undefined) {
                    delete this.invalid[i.name]
                } else {
                    this.prepareElement(g);
                    this.currentElements = c(g);
                    j = this.groups[g.name];
                    if (j) {
                        c.each(this.groups, function (l, k) {
                            if (k === j && l !== g.name) {
                                i = f.validationTargetFor(f.clean(f.findByName(l)));
                                if (i && i.name in f.invalid) {
                                    f.currentElements.push(i);
                                    d = f.check(i) && d
                                }
                            }
                        })
                    }
                    e = this.check(g) !== false;
                    d = d && e;
                    if (e) {
                        this.invalid[g.name] = false
                    } else {
                        this.invalid[g.name] = true
                    }
                    if (!this.numberOfInvalids()) {
                        this.toHide = this.toHide.add(this.containers)
                    }
                    this.showErrors();
                    c(h).attr("aria-invalid", !e)
                }
                return d
            }, showErrors: function (e) {
                if (e) {
                    var d = this;
                    c.extend(this.errorMap, e);
                    this.errorList = c.map(this.errorMap, function (g, f) {
                        return {message: g, element: d.findByName(f)[0]}
                    });
                    this.successList = c.grep(this.successList, function (f) {
                        return !(f.name in e)
                    })
                }
                if (this.settings.showErrors) {
                    this.settings.showErrors.call(this, this.errorMap, this.errorList)
                } else {
                    this.defaultShowErrors()
                }
            }, resetForm: function () {
                if (c.fn.resetForm) {
                    c(this.currentForm).resetForm()
                }
                this.invalid = {};
                this.submitted = {};
                this.prepareForm();
                this.hideErrors();
                var d = this.elements().removeData("previousValue").removeAttr("aria-invalid");
                this.resetElements(d)
            }, resetElements: function (e) {
                var d;
                if (this.settings.unhighlight) {
                    for (d = 0; e[d]; d++) {
                        this.settings.unhighlight.call(this, e[d], this.settings.errorClass, "");
                        this.findByName(e[d].name).removeClass(this.settings.validClass)
                    }
                } else {
                    e.removeClass(this.settings.errorClass).removeClass(this.settings.validClass)
                }
            }, numberOfInvalids: function () {
                return this.objectLength(this.invalid)
            }, objectLength: function (f) {
                var e = 0, d;
                for (d in f) {
                    if (f[d]) {
                        e++
                    }
                }
                return e
            }, hideErrors: function () {
                this.hideThese(this.toHide)
            }, hideThese: function (d) {
                d.not(this.containers).text("");
                this.addWrapper(d).hide()
            }, valid: function () {
                return this.size() === 0
            }, size: function () {
                return this.errorList.length
            }, focusInvalid: function () {
                if (this.settings.focusInvalid) {
                    try {
                        c(this.findLastActive() || this.errorList.length && this.errorList[0].element || []).filter(":visible").focus().trigger("focusin")
                    } catch (d) {
                    }
                }
            }, findLastActive: function () {
                var d = this.lastActive;
                return d && c.grep(this.errorList, function (e) {
                    return e.element.name === d.name
                }).length === 1 && d
            }, elements: function () {
                var e = this, d = {};
                return c(this.currentForm).find("input, select, textarea, [contenteditable]").not(":submit, :reset, :image, :disabled").not(this.settings.ignore).filter(function () {
                    var f = this.name || c(this).attr("name");
                    if (!f && e.settings.debug && window.console) {
                        console.error("%o has no name assigned", this)
                    }
                    if (this.hasAttribute("contenteditable")) {
                        this.form = c(this).closest("form")[0]
                    }
                    if (f in d || !e.objectLength(c(this).rules())) {
                        return false
                    }
                    d[f] = true;
                    return true
                })
            }, clean: function (d) {
                return c(d)[0]
            }, errors: function () {
                var d = this.settings.errorClass.split(" ").join(".");
                return c(this.settings.errorElement + "." + d, this.errorContext)
            }, resetInternals: function () {
                this.successList = [];
                this.errorList = [];
                this.errorMap = {};
                this.toShow = c([]);
                this.toHide = c([])
            }, reset: function () {
                this.resetInternals();
                this.currentElements = c([])
            }, prepareForm: function () {
                this.reset();
                this.toHide = this.errors().add(this.containers)
            }, prepareElement: function (d) {
                this.reset();
                this.toHide = this.errorsFor(d)
            }, elementValue: function (f) {
                var e = c(f), g = f.type, h, d;
                if (g === "radio" || g === "checkbox") {
                    return this.findByName(f.name).filter(":checked").val()
                } else {
                    if (g === "number" && typeof f.validity !== "undefined") {
                        return f.validity.badInput ? "NaN" : e.val()
                    }
                }
                if (f.hasAttribute("contenteditable")) {
                    h = e.text()
                } else {
                    h = e.val()
                }
                if (g === "file") {
                    if (h.substr(0, 12) === "C:\\fakepath\\") {
                        return h.substr(12)
                    }
                    d = h.lastIndexOf("/");
                    if (d >= 0) {
                        return h.substr(d + 1)
                    }
                    d = h.lastIndexOf("\\");
                    if (d >= 0) {
                        return h.substr(d + 1)
                    }
                    return h
                }
                if (typeof h === "string") {
                    return h.replace(/\r/g, "")
                }
                return h
            }, check: function (g) {
                g = this.validationTargetFor(this.clean(g));
                var k = c(g).rules(), i = c.map(k, function (o, e) {
                    return e
                }).length, l = false, f = this.elementValue(g), m, d, j;
                if (typeof k.normalizer === "function") {
                    f = k.normalizer.call(g, f);
                    if (typeof f !== "string") {
                        throw new TypeError("The normalizer should return a string value.")
                    }
                    delete k.normalizer
                }
                for (d in k) {
                    j = {method: d, parameters: k[d]};
                    try {
                        m = c.validator.methods[d].call(this, f, g, j.parameters);
                        if (m === "dependency-mismatch" && i === 1) {
                            l = true;
                            continue
                        }
                        l = false;
                        if (m === "pending") {
                            this.toHide = this.toHide.not(this.errorsFor(g));
                            return
                        }
                        if (!m) {
                            this.formatAndAdd(g, j);
                            return false
                        }
                    } catch (h) {
                        if (this.settings.debug && window.console) {
                            console.log("Exception occurred when checking element " + g.id + ", check the '" + j.method + "' method.", h)
                        }
                        if (h instanceof TypeError) {
                            h.message += ".  Exception occurred when checking element " + g.id + ", check the '" + j.method + "' method."
                        }
                        throw h
                    }
                }
                if (l) {
                    return
                }
                if (this.objectLength(k)) {
                    this.successList.push(g)
                }
                return true
            }, customDataMessage: function (d, e) {
                return c(d).data("msg" + e.charAt(0).toUpperCase() + e.substring(1).toLowerCase()) || c(d).data("msg")
            }, customMessage: function (e, f) {
                var d = this.settings.messages[e];
                return d && (d.constructor === String ? d : d[f])
            }, findDefined: function () {
                for (var d = 0; d < arguments.length; d++) {
                    if (arguments[d] !== undefined) {
                        return arguments[d]
                    }
                }
                return undefined
            }, defaultMessage: function (e, g) {
                if (typeof g === "string") {
                    g = {method: g}
                }
                var f = this.findDefined(this.customMessage(e.name, g.method), this.customDataMessage(e, g.method), !this.settings.ignoreTitle && e.title || undefined, c.validator.messages[g.method], "<strong>Warning: No message defined for " + e.name + "</strong>"),
                    d = /\$?\{(\d+)\}/g;
                if (typeof f === "function") {
                    f = f.call(this, g.parameters, e)
                } else {
                    if (d.test(f)) {
                        f = c.validator.format(f.replace(d, "{$1}"), g.parameters)
                    }
                }
                return f
            }, formatAndAdd: function (d, f) {
                var e = this.defaultMessage(d, f);
                this.errorList.push({message: e, element: d, method: f.method});
                this.errorMap[d.name] = e;
                this.submitted[d.name] = e
            }, addWrapper: function (d) {
                if (this.settings.wrapper) {
                    d = d.add(d.parent(this.settings.wrapper))
                }
                return d
            }, defaultShowErrors: function () {
                var e, f, d;
                for (e = 0; this.errorList[e]; e++) {
                    d = this.errorList[e];
                    if (this.settings.highlight) {
                        this.settings.highlight.call(this, d.element, this.settings.errorClass, this.settings.validClass)
                    }
                    this.showLabel(d.element, d.message)
                }
                if (this.errorList.length) {
                    this.toShow = this.toShow.add(this.containers)
                }
                if (this.settings.success) {
                    for (e = 0; this.successList[e]; e++) {
                        this.showLabel(this.successList[e])
                    }
                }
                if (this.settings.unhighlight) {
                    for (e = 0, f = this.validElements(); f[e]; e++) {
                        this.settings.unhighlight.call(this, f[e], this.settings.errorClass, this.settings.validClass)
                    }
                }
                this.toHide = this.toHide.not(this.toShow);
                this.hideErrors();
                this.addWrapper(this.toShow).show()
            }, validElements: function () {
                return this.currentElements.not(this.invalidElements())
            }, invalidElements: function () {
                return c(this.errorList).map(function () {
                    return this.element
                })
            }, showLabel: function (g, l) {
                var e, j, f, k, h = this.errorsFor(g), i = this.idOrName(g), d = c(g).attr("aria-describedby");
                if (h.length) {
                    h.removeClass(this.settings.validClass).addClass(this.settings.errorClass);
                    h.html(l)
                } else {
                    h = c("<" + this.settings.errorElement + ">").attr("id", i + "-error").addClass(this.settings.errorClass).html(l || "");
                    e = h;
                    if (this.settings.wrapper) {
                        e = h.hide().show().wrap("<" + this.settings.wrapper + "/>").parent()
                    }
                    if (this.labelContainer.length) {
                        this.labelContainer.append(e)
                    } else {
                        if (this.settings.errorPlacement) {
                            this.settings.errorPlacement.call(this, e, c(g))
                        } else {
                            e.insertAfter(g)
                        }
                    }
                    if (h.is("label")) {
                        h.attr("for", i)
                    } else {
                        if (h.parents("label[for='" + this.escapeCssMeta(i) + "']").length === 0) {
                            f = h.attr("id");
                            if (!d) {
                                d = f
                            } else {
                                if (!d.match(new RegExp("\\b" + this.escapeCssMeta(f) + "\\b"))) {
                                    d += " " + f
                                }
                            }
                            c(g).attr("aria-describedby", d);
                            j = this.groups[g.name];
                            if (j) {
                                k = this;
                                c.each(k.groups, function (n, m) {
                                    if (m === j) {
                                        c("[name='" + k.escapeCssMeta(n) + "']", k.currentForm).attr("aria-describedby", h.attr("id"))
                                    }
                                })
                            }
                        }
                    }
                }
                if (!l && this.settings.success) {
                    h.text("");
                    if (typeof this.settings.success === "string") {
                        h.addClass(this.settings.success)
                    } else {
                        this.settings.success(h, g)
                    }
                }
                this.toShow = this.toShow.add(h)
            }, errorsFor: function (f) {
                var e = this.escapeCssMeta(this.idOrName(f)), g = c(f).attr("aria-describedby"),
                    d = "label[for='" + e + "'], label[for='" + e + "'] *";
                if (g) {
                    d = d + ", #" + this.escapeCssMeta(g).replace(/\s+/g, ", #")
                }
                return this.errors().filter(d)
            }, escapeCssMeta: function (d) {
                return d.replace(/([\\!"#$%&'()*+,./:;<=>?@\[\]^`{|}~])/g, "\\$1")
            }, idOrName: function (d) {
                return this.groups[d.name] || (this.checkable(d) ? d.name : d.id || d.name)
            }, validationTargetFor: function (d) {
                if (this.checkable(d)) {
                    d = this.findByName(d.name)
                }
                return c(d).not(this.settings.ignore)[0]
            }, checkable: function (d) {
                return (/radio|checkbox/i).test(d.type)
            }, findByName: function (d) {
                return c(this.currentForm).find("[name='" + this.escapeCssMeta(d) + "']")
            }, getLength: function (e, d) {
                switch (d.nodeName.toLowerCase()) {
                    case"select":
                        return c("option:selected", d).length;
                    case"input":
                        if (this.checkable(d)) {
                            return this.findByName(d.name).filter(":checked").length
                        }
                }
                return e.length
            }, depend: function (e, d) {
                return this.dependTypes[typeof e] ? this.dependTypes[typeof e](e, d) : true
            }, dependTypes: {
                "boolean": function (d) {
                    return d
                }, string: function (e, d) {
                    return !!c(e, d.form).length
                }, "function": function (e, d) {
                    return e(d)
                }
            }, optional: function (d) {
                var e = this.elementValue(d);
                return !c.validator.methods.required.call(this, e, d) && "dependency-mismatch"
            }, startRequest: function (d) {
                if (!this.pending[d.name]) {
                    this.pendingRequest++;
                    c(d).addClass(this.settings.pendingClass);
                    this.pending[d.name] = true
                }
            }, stopRequest: function (d, e) {
                this.pendingRequest--;
                if (this.pendingRequest < 0) {
                    this.pendingRequest = 0
                }
                delete this.pending[d.name];
                c(d).removeClass(this.settings.pendingClass);
                if (e && this.pendingRequest === 0 && this.formSubmitted && this.form()) {
                    c(this.currentForm).submit();
                    this.formSubmitted = false
                } else {
                    if (!e && this.pendingRequest === 0 && this.formSubmitted) {
                        c(this.currentForm).triggerHandler("invalid-form", [this]);
                        this.formSubmitted = false
                    }
                }
            }, previousValue: function (d, e) {
                e = typeof e === "string" && e || "remote";
                return c.data(d, "previousValue") || c.data(d, "previousValue", {
                    old: null,
                    valid: true,
                    message: this.defaultMessage(d, {method: e})
                })
            }, destroy: function () {
                this.resetForm();
                c(this.currentForm).off(".validate").removeData("validator").find(".validate-equalTo-blur").off(".validate-equalTo").removeClass("validate-equalTo-blur")
            }
        },
        classRuleSettings: {
            required: {required: true},
            Chinese: {Chinese: true},
            nae: {nae: true},
            English: {English: true},
            date: {date: true},
            dateISO: {dateISO: true},
            number: {number: true},
            digits: {digits: true},
            creditcard: {creditcard: true}
        },
        addClassRules: function (d, e) {
            if (d.constructor === String) {
                this.classRuleSettings[d] = e
            } else {
                c.extend(this.classRuleSettings, d)
            }
        },
        classRules: function (e) {
            var f = {}, d = c(e).attr("class");
            if (d) {
                c.each(d.split(" "), function () {
                    if (this in c.validator.classRuleSettings) {
                        c.extend(f, c.validator.classRuleSettings[this])
                    }
                })
            }
            return f
        },
        normalizeAttributeRule: function (f, d, g, e) {
            if (/min|max|step/.test(g) && (d === null || /number|range|text/.test(d))) {
                e = Number(e);
                if (isNaN(e)) {
                    e = undefined
                }
            }
            if (e || e === 0) {
                f[g] = e
            } else {
                if (d === g && d !== "range") {
                    f[g] = true
                }
            }
        },
        attributeRules: function (e) {
            var h = {}, d = c(e), f = e.getAttribute("type"), i, g;
            for (i in c.validator.methods) {
                if (i === "required") {
                    g = e.getAttribute(i);
                    if (g === "") {
                        g = true
                    }
                    g = !!g
                } else {
                    g = d.attr(i)
                }
                this.normalizeAttributeRule(h, f, i, g)
            }
            if (h.maxlength && /-1|2147483647|524288/.test(h.maxlength)) {
                delete h.maxlength
            }
            return h
        },
        dataRules: function (e) {
            var h = {}, d = c(e), f = e.getAttribute("type"), i, g;
            for (i in c.validator.methods) {
                g = d.data("rule" + i.charAt(0).toUpperCase() + i.substring(1).toLowerCase());
                this.normalizeAttributeRule(h, f, i, g)
            }
            return h
        },
        staticRules: function (e) {
            var f = {}, d = c.data(e.form, "validator");
            if (d.settings.rules) {
                f = c.validator.normalizeRule(d.settings.rules[e.name]) || {}
            }
            return f
        },
        normalizeRules: function (e, d) {
            c.each(e, function (h, g) {
                if (g === false) {
                    delete e[h];
                    return
                }
                if (g.param || g.depends) {
                    var f = true;
                    switch (typeof g.depends) {
                        case"string":
                            f = !!c(g.depends, d.form).length;
                            break;
                        case"function":
                            f = g.depends.call(d, d);
                            break
                    }
                    if (f) {
                        e[h] = g.param !== undefined ? g.param : true
                    } else {
                        c.data(d.form, "validator").resetElements(c(d));
                        delete e[h]
                    }
                }
            });
            c.each(e, function (f, g) {
                e[f] = c.isFunction(g) && f !== "normalizer" ? g(d) : g
            });
            c.each(["minlength", "maxlength"], function () {
                if (e[this]) {
                    e[this] = Number(e[this])
                }
            });
            c.each(["rangelength", "range"], function () {
                var f;
                if (e[this]) {
                    if (c.isArray(e[this])) {
                        e[this] = [Number(e[this][0]), Number(e[this][1])]
                    } else {
                        if (typeof e[this] === "string") {
                            f = e[this].replace(/[\[\]]/g, "").split(/[\s,]+/);
                            e[this] = [Number(f[0]), Number(f[1])]
                        }
                    }
                }
            });
            if (c.validator.autoCreateRanges) {
                if (e.min != null && e.max != null) {
                    e.range = [e.min, e.max];
                    delete e.min;
                    delete e.max
                }
                if (e.minlength != null && e.maxlength != null) {
                    e.rangelength = [e.minlength, e.maxlength];
                    delete e.minlength;
                    delete e.maxlength
                }
            }
            return e
        },
        normalizeRule: function (e) {
            if (typeof e === "string") {
                var d = {};
                c.each(e.split(/\s/), function () {
                    d[this] = true
                });
                e = d
            }
            return e
        },
        addMethod: function (d, f, e) {
            c.validator.methods[d] = f;
            c.validator.messages[d] = e !== undefined ? e : c.validator.messages[d];
            if (f.length < 3) {
                c.validator.addClassRules(d, c.validator.normalizeRule(d))
            }
        },
        methods: {
            required: function (e, d, g) {
                if (!this.depend(g, d)) {
                    return "dependency-mismatch"
                }
                if (d.nodeName.toLowerCase() === "select") {
                    var f = c(d).val();
                    return f && f.length > 0
                }
                if (this.checkable(d)) {
                    return this.getLength(e, d) > 0
                }
                return e.length > 0
            //}, email: function (e, d) {
              }, Chinese: function (e, d) {
                return this.optional(d) || /^[\u4e00-\u9fa5]{1,20}$/.test(e)
            //}, url: function (e, d) {
              }, nae: function (e, d) {
                return this.optional(d) || /^[A-Za-z0-9]+$/.test(e)
            }, English: function (e, d) {
                return this.optional(d) || /^[A-Za-z]+$/.test(e)
            }, date: function (e, d) {
                //return this.optional(d) || !/Invalid|NaN/.test(new Date(e).toString())
                return this.optional(d) ||  /^((?!0000)[0-9]{4}((0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)0229)$/.test(e)
            }, dateISO: function (e, d) {
                return this.optional(d) || /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/.test(e)
            }, number: function (e, d) {
                //return this.optional(d) || /^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(e)
                //return this.optional(d) || /^0\.\d+$|^([1-9])+(\.\d+)?$/.test(e)
                return this.optional(d) || /^([1-9][0-9]*)(\.[0-9]*)?$|^(0\.[0-9]*)$/.test(e)
            }, digits: function (e, d) {
                return this.optional(d) || /^\d+$/.test(e)
            }, minlength: function (f, d, g) {
                var e = c.isArray(f) ? f.length : this.getLength(f, d);
                return this.optional(d) || e >= g
            }, maxlength: function (f, d, g) {
                var e = c.isArray(f) ? f.length : this.getLength(f, d);
                return this.optional(d) || e <= g
            }, rangelength: function (f, d, g) {
                var e = c.isArray(f) ? f.length : this.getLength(f, d);
                return this.optional(d) || (e >= g[0] && e <= g[1])
            }, min: function (e, d, f) {
                return this.optional(d) || e >= f
            }, max: function (e, d, f) {
                return this.optional(d) || e <= f
            }, range: function (e, d, f) {
                return this.optional(d) || (e >= f[0] && e <= f[1])
            }, step: function (n, h, f) {
                var m = c(h).attr("type"), l = "Step attribute on input type " + m + " is not supported.",
                    k = ["text", "number", "range"], o = new RegExp("\\b" + m + "\\b"), i = m && !o.test(k.join()),
                    g = function (q) {
                        var p = ("" + q).match(/(?:\.(\d+))?$/);
                        if (!p) {
                            return 0
                        }
                        return p[1] ? p[1].length : 0
                    }, j = function (p) {
                        return Math.round(p * Math.pow(10, e))
                    }, d = true, e;
                if (i) {
                    throw new Error(l)
                }
                e = g(f);
                if (g(n) > e || j(n) % j(f) !== 0) {
                    d = false
                }
                return this.optional(h) || d
            }, equalTo: function (e, d, g) {
                var f = c(g);
                if (this.settings.onfocusout && f.not(".validate-equalTo-blur").length) {
                    f.addClass("validate-equalTo-blur").on("blur.validate-equalTo", function () {
                        c(d).valid()
                    })
                }
                return e === f.val()
            }, remote: function (i, e, j, k) {
                if (this.optional(e)) {
                    return "dependency-mismatch"
                }
                k = typeof k === "string" && k || "remote";
                var f = this.previousValue(e, k), d, h, g;
                if (!this.settings.messages[e.name]) {
                    this.settings.messages[e.name] = {}
                }
                f.originalMessage = f.originalMessage || this.settings.messages[e.name][k];
                this.settings.messages[e.name][k] = f.message;
                j = typeof j === "string" && {nae: encodeURI(j)} || j;
                g = c.param(c.extend({data: i}, j.data));
                if (f.old === g) {
                    return f.valid
                }
                f.old = g;
                d = this;
                this.startRequest(e);
                h = {};
                h[e.name] = i;
                c.ajax(c.extend(true, {
                    mode: "abort",
                    port: "validate" + e.name,
                    dataType: "json",
                    data: h,
                    context: d.currentForm,
                    success: function (m) {
                        var o = m === true || m === "true", p, n, l;
                        d.settings.messages[e.name][k] = f.originalMessage;
                        if (o) {
                            l = d.formSubmitted;
                            d.resetInternals();
                            d.toHide = d.errorsFor(e);
                            d.formSubmitted = l;
                            d.successList.push(e);
                            d.invalid[e.name] = false;
                            d.showErrors()
                        } else {
                            p = {};
                            n = m || d.defaultMessage(e, {method: k, parameters: i});
                            p[e.name] = f.message = n;
                            d.invalid[e.name] = true;
                            d.showErrors(p)
                        }
                        f.valid = o;
                        d.stopRequest(e, o)
                    }
                }, j));
                return "pending"
            }
        }
    });
    var a = {}, b;
    if (c.ajaxPrefilter) {
        c.ajaxPrefilter(function (f, e, g) {
            var d = f.port;
            if (f.mode === "abort") {
                if (a[d]) {
                    a[d].abort()
                }
                a[d] = g
            }
        })
    } else {
        b = c.ajax;
        c.ajax = function (e) {
            var f = ("mode" in e ? e : c.ajaxSettings).mode, d = ("port" in e ? e : c.ajaxSettings).port;
            if (f === "abort") {
                if (a[d]) {
                    a[d].abort()
                }
                a[d] = b.apply(this, arguments);
                return a[d]
            }
            return b.apply(this, arguments)
        }
    }
    return c
}));*/
(function (a) {
    a("#inputForm .box-footer [class*=col-sm-offset]").append('<div class="form-error"></div>');
    a.extend(a.validator.default, {
        ignore: ":hidden:not(.required),input.select2-focusser",
        errorClass: "has-error",
        errorContainer: ".form-error",
        errorPlacement: function (b, c) {
            if (c.closest(".icheck").size() > 0) {
                c = c.closest(".icheck");
                c.parent().css("position", "relative");
                b.insertAfter(c);
                b.css({top: c.position().top + c.outerHeight() + 2, left: c.position().left + 5});
                return
            }
            if (c.next().hasClass("select2")) {
                c = c.next();
                c.parent().css("position", "relative")
            } else {
                if (c.closest(".input-group").length > 0) {
                    c = c.closest(".input-group");
                    c.parent().css("position", "relative")
                }
            }
            b.insertAfter(c);
            b.css({top: c.position().top + c.outerHeight() - 5, left: c.position().left + 5})
        },
        highlight: function (b) {
            a(b).closest(".form-group").addClass("has-error")
        },
        unhighlight: function (b) {
            a(b).closest(".form-group").removeClass("has-error")
        },
        success: function (b) {
            b.remove()
        }
    })
}(jQuery));
jQuery.validator.addMethod("userName", function (b, a) {
    return this.optional(a) || /^[\u0391-\uFFE5\w]+$/.test(b)
//}, $.validator.messages.userName);
}, "输入错误!");
jQuery.validator.addMethod("realName", function (b, a) {
    return this.optional(a) || /^[\u4e00-\u9fa5]{2,30}$/.test(b)
}, "输入错误!");
jQuery.validator.addMethod("abc", function (b, a) {
    return this.optional(a) || /^[a-zA-Z0-9_]*$/.test(b)
}, "输入错误!");
jQuery.validator.addMethod("noEqualTo", function (b, a, c) {
    return b != $(c).val()
}, "输入错误!");
jQuery.validator.addMethod("mobile", function (c, b) {
    var a = /^1[3,4,5,6,7,8,9]\d{9}$/g;
    return this.optional(b) || (a.test(c))
}, "输入错误!");
jQuery.validator.addMethod("simplePhone", function (c, b) {
    var a = /^(\d{3,4}-?)?\d{7,9}$/g;
    return this.optional(b) || (a.test(c))
}, "输入错误!");
jQuery.validator.addMethod("phone", function (c, b) {
    var a = /(^0[1-9]{1}\d{8,10}$)|(^1[3,4,5,6,7,8,9]\d{9}$)/g;
    return this.optional(b) || (a.test(c))
}, "输入错误!");
jQuery.validator.addMethod("zipCode", function (c, b) {
    var a = /^[0-9]{6}$/;
    return this.optional(b) || (a.test(c))
}, "输入错误!");
$.validator.addMethod("integer", function (b, a) {
    return this.optional(a) || /^-?\d+$/.test(b)
}, "输入错误!");
$.validator.addMethod("ipv4", function (b, a) {
    return this.optional(a) || /^(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)$/i.test(b)
},"输入错误!");
$.validator.addMethod("ipv6", function (b, a) {
    return this.optional(a) || /^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))$/i.test(b)
}, "输入错误!");
jQuery.validator.addMethod("qq", function (c, b) {
    var a = /^[1-9][0-9]{4,}$/;
    return this.optional(b) || (a.test(c))
}, "输入错误!");
jQuery.validator.addMethod("Chinese", function (c, b) {
    var a = /^[\u4e00-\u9fa5]{1,20}$/;
    return this.optional(b) || (a.test(c))
}, "请输入有效中文!");
jQuery.validator.addMethod("nae", function (c, b) {
    var a = /^[A-Za-z0-9]+$/;
    return this.optional(b) || (a.test(c))
}, "请输入有效数字或英文!");
jQuery.validator.addMethod("English", function (c, b) {
    var a = /^[A-Za-z]+$/;
    return this.optional(b) || (a.test(c))
}, "请输入有效英文!");
jQuery.validator.addMethod("date", function (c, b) {
    var a = /^((?!0000)[0-9]{4}((0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)0229)$/;
    return this.optional(b) || (a.test(c))
}, "请输入正确日期!格式:20190501");
jQuery.validator.addMethod("number", function (c, b) {
    var a = /^([1-9][0-9]*)(\.[0-9]*)?$|^(0\.[0-9]*)$/;
    return this.optional(b) || (a.test(c))
}, "请输入有效数值!");
jQuery.validator.addMethod("digits", function (c, b) {
    var a = /^\d+$/;
    return this.optional(b) || (a.test(c))
}, "请输入有效数字!");
jQuery.validator.addMethod("idcard", function (c, a) {
    var b = {
        provinceAndCitys: {
            11: "北京",
            12: "天津",
            13: "河北",
            14: "山西",
            15: "内蒙古",
            21: "辽宁",
            22: "吉林",
            23: "黑龙江",
            31: "上海",
            32: "江苏",
            33: "浙江",
            34: "安徽",
            35: "福建",
            36: "江西",
            37: "山东",
            41: "河南",
            42: "湖北",
            43: "湖南",
            44: "广东",
            45: "广西",
            46: "海南",
            50: "重庆",
            51: "四川",
            52: "贵州",
            53: "云南",
            54: "西藏",
            61: "陕西",
            62: "甘肃",
            63: "青海",
            64: "宁夏",
            65: "新疆",
            71: "台湾",
            81: "香港",
            82: "澳门",
            91: "国外"
        },
        powers: ["7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"],
        parityBit: ["1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"],
        genders: {male: "男", female: "女"},
        checkAddressCode: function (e) {
            var d = /^[1-9]\d{5}$/.test(e);
            if (!d) {
                return false
            }
            if (b.provinceAndCitys[parseInt(e.substring(0, 2))]) {
                return true
            } else {
                return false
            }
        },
        checkBirthDayCode: function (i) {
            var f = /^[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/.test(i);
            if (!f) {
                return false
            }
            var h = parseInt(i.substring(0, 4), 10);
            var g = parseInt(i.substring(4, 6), 10);
            var d = parseInt(i.substring(6), 10);
            var e = new Date(h, g - 1, d);
            if (e > new Date()) {
                return false
            } else {
                if ((e.getFullYear() == h) && (e.getMonth() == g - 1) && (e.getDate() == d)) {
                    return true
                } else {
                    return false
                }
            }
        },
        getParityBit: function (g) {
            var h = g.substring(0, 17);
            var f = 0;
            for (var e = 0; e < 17; e++) {
                f += parseInt(h.charAt(e), 10) * parseInt(b.powers[e])
            }
            var d = f % 11;
            return b.parityBit[d]
        },
        checkParityBit: function (e) {
            var d = e.charAt(17).toUpperCase();
            if (b.getParityBit(e) == d) {
                return true
            } else {
                return false
            }
        },
        checkIdCardNo: function (e) {
            var d = /^\d{15}|(\d{17}(\d|x|X))$/.test(e);
            if (!d) {
                return false
            }
            if (e.length == 15) {
                return b.check15IdCardNo(e)
            } else {
                if (e.length == 18) {
                    return b.check18IdCardNo(e)
                } else {
                    return false
                }
            }
        },
        check15IdCardNo: function (f) {
            var d = /^[1-9]\d{7}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}$/.test(f);
            if (!d) {
                return false
            }
            var e = f.substring(0, 6);
            d = b.checkAddressCode(e);
            if (!d) {
                return false
            }
            var g = "19" + f.substring(6, 12);
            return b.checkBirthDayCode(g)
        },
        check18IdCardNo: function (f) {
            var d = /^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}(\d|x|X)$/.test(f);
            if (!d) {
                return false
            }
            var e = f.substring(0, 6);
            d = b.checkAddressCode(e);
            if (!d) {
                return false
            }
            var g = f.substring(6, 14);
            d = b.checkBirthDayCode(g);
            if (!d) {
                return false
            }
            return b.checkParityBit(f)
        },
        formateDateCN: function (e) {
            var g = e.substring(0, 4);
            var f = e.substring(4, 6);
            var d = e.substring(6);
            return g + "-" + f + "-" + d
        },
        getIdCardInfo: function (e) {
            var d = {gender: "", birthday: ""};
            if (e.length == 15) {
                var f = "19" + e.substring(6, 12);
                d.birthday = b.formateDateCN(f);
                if (parseInt(e.charAt(14)) % 2 == 0) {
                    d.gender = b.genders.female
                } else {
                    d.gender = b.genders.male
                }
            } else {
                if (e.length == 18) {
                    var f = e.substring(6, 14);
                    d.birthday = b.formateDateCN(f);
                    if (parseInt(e.charAt(16)) % 2 == 0) {
                        d.gender = b.genders.female
                    } else {
                        d.gender = b.genders.male
                    }
                }
            }
            return d
        },
        getId15: function (d) {
            if (d.length == 15) {
                return d
            } else {
                if (d.length == 18) {
                    return d.substring(0, 6) + d.substring(8, 17)
                } else {
                    return null
                }
            }
        },
        getId18: function (e) {
            if (e.length == 15) {
                var f = e.substring(0, 6) + "19" + e.substring(6);
                var d = b.getParityBit(f);
                return f + d
            } else {
                if (e.length == 18) {
                    return e
                } else {
                    return null
                }
            }
        }
    };
    return this.optional(a) || b.checkIdCardNo(c)
}, $.validator.messages.idcard);

