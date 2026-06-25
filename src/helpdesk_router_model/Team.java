package helpdesk_router_model;

public enum Team {

        HELP_DESK(
                new KeywordHolder("okta", 10), new KeywordHolder("yubikey", 10), new KeywordHolder("eduroam", 10), new KeywordHolder("globalprotect", 10), new KeywordHolder("authenticator",10), new KeywordHolder("mapped",10), new KeywordHolder("map",10), new KeywordHolder("mapping", 2),
                new KeywordHolder("password", 5), new KeywordHolder("unlock", 5), new KeywordHolder("mfa", 5), new KeywordHolder("canvas", 5), new KeywordHolder("vpn", 5), new KeywordHolder("reservation", 5), new KeywordHolder("checkout", 5), new KeywordHolder("wifi", 5), new KeywordHolder("wireless", 5),
                new KeywordHolder("login", 2), new KeywordHolder("browser", 2), new KeywordHolder("chrome", 2), new KeywordHolder("edge", 2), new KeywordHolder("safari", 2), new KeywordHolder("cache", 2), new KeywordHolder("cookies", 2), new KeywordHolder("zoom", 2), new KeywordHolder("word", 2), new KeywordHolder("outlook", 2), new KeywordHolder("office", 2),  new KeywordHolder("drive", 2), new KeywordHolder("printer", 2), new KeywordHolder("software", 2), new KeywordHolder("install", 2), new KeywordHolder("network", 2), new KeywordHolder("internet", 2), new KeywordHolder("access", 2), new KeywordHolder("account", 2)
        ),

        HELP_DESK_CALEB(
                new KeywordHolder("docusign", 10), new KeywordHolder("ivanti", 10), new KeywordHolder("spss", 10), new KeywordHolder("ehive", 10),
                new KeywordHolder("reactivation", 5), new KeywordHolder("username", 5), new KeywordHolder("cengage", 5), new KeywordHolder("reenrolled", 5),
                new KeywordHolder("name", 2), new KeywordHolder("change", 2), new KeywordHolder("email", 2), new KeywordHolder("balance", 2), new KeywordHolder("alumni", 2)
        ),

        END_POINT(
                new KeywordHolder("bitlocker", 10), new KeywordHolder("jabber", 10), new KeywordHolder("mfp", 10), new KeywordHolder("copier", 10),
                new KeywordHolder("hardware", 5), new KeywordHolder("laptop", 5), new KeywordHolder("desktop", 5), new KeywordHolder("iphone", 5), new KeywordHolder("ipad", 5), new KeywordHolder("telephone", 5), new KeywordHolder("voicemail", 5), new KeywordHolder("recoverykey", 5),
                new KeywordHolder("computer", 2), new KeywordHolder("phone", 2), new KeywordHolder("keyboard", 2), new KeywordHolder("mouse", 2), new KeywordHolder("monitor", 2), new KeywordHolder("screen", 2), new KeywordHolder("camera", 2), new KeywordHolder("docking", 2), new KeywordHolder("station", 2), new KeywordHolder("headset", 2), new KeywordHolder("speaker", 2), new KeywordHolder("microphone", 2), new KeywordHolder("printer", 2), new KeywordHolder("scanner", 2), new KeywordHolder("toner", 2), new KeywordHolder("ios", 2), new KeywordHolder("mobile", 2), new KeywordHolder("tablet", 2), new KeywordHolder("adapter", 2), new KeywordHolder("broken", 2), new KeywordHolder("device", 2), new KeywordHolder("replacement", 2), new KeywordHolder("setup", 2), new KeywordHolder("onboarding", 2), new KeywordHolder("employee", 2), new KeywordHolder("workstation", 2)
        ),

        APPLICATION_DEVELOPMENT(
                new KeywordHolder("peoplesoft", 10), new KeywordHolder("imagenow", 10), new KeywordHolder("qualtrics", 10), new KeywordHolder("bplogix", 10), new KeywordHolder("eab", 10),
                new KeywordHolder("navigate", 5), new KeywordHolder("schedule", 5), new KeywordHolder("scholarship", 5), new KeywordHolder("report", 5), new KeywordHolder("query", 5), new KeywordHolder("perceptive", 5),
                new KeywordHolder("class", 2), new KeywordHolder("course", 2), new KeywordHolder("page", 2), new KeywordHolder("data", 2), new KeywordHolder("request", 2), new KeywordHolder("content", 2)
        ),

        SYSTEM_ADMINISTRATORS(
                new KeywordHolder("makemeadmin", 10), new KeywordHolder("barracuda", 10), new KeywordHolder("sharepoint", 10),
                new KeywordHolder("license", 5), new KeywordHolder("distribution", 5), new KeywordHolder("mailbox", 5), new KeywordHolder("permissions", 5), new KeywordHolder("storage", 5), new KeywordHolder("digest", 5),
                new KeywordHolder("email", 2), new KeywordHolder("account", 2), new KeywordHolder("shared", 2), new KeywordHolder("inbox", 2), new KeywordHolder("network", 2), new KeywordHolder("drive", 2), new KeywordHolder("spam", 2), new KeywordHolder("admin", 2), new KeywordHolder("access", 2), new KeywordHolder("creation", 2)
        ),

        SECURITY(
                new KeywordHolder("phishing", 10), new KeywordHolder("suspicious", 10),
                new KeywordHolder("virus", 5), new KeywordHolder("usb", 5), new KeywordHolder("removable", 5), new KeywordHolder("alert", 5),
                new KeywordHolder("threat", 2), new KeywordHolder("blocked", 2), new KeywordHolder("policy", 2), new KeywordHolder("exception", 2), new KeywordHolder("media", 2)
        ),

        CLASSROOM_AND_LABS(
                new KeywordHolder("projector", 10), new KeywordHolder("podium", 10), new KeywordHolder("classroom", 10),
                new KeywordHolder("lab", 5), new KeywordHolder("tv", 5), new KeywordHolder("controlpanel", 5),
                new KeywordHolder("computer", 2), new KeywordHolder("printer", 2), new KeywordHolder("software", 2), new KeywordHolder("install", 2), new KeywordHolder("screen", 2)
        ),

        RESNET(
                new KeywordHolder("resnet", 10), new KeywordHolder("dorm", 10),
                new KeywordHolder("residence", 5), new KeywordHolder("ethernet", 5),
                new KeywordHolder("hall", 2), new KeywordHolder("port", 2), new KeywordHolder("computer", 2), new KeywordHolder("printer", 2), new KeywordHolder("internet", 2)
        ),

        UNIVERSITY_RELATIONS(
                new KeywordHolder("webpage", 10),
                new KeywordHolder("website", 5), new KeywordHolder("webupdate", 5),
                new KeywordHolder("web", 2), new KeywordHolder("update", 2), new KeywordHolder("page", 2)
        ),

        OUTREACH(
                new KeywordHolder("newsletter", 10),
                new KeywordHolder("technology", 5),
                new KeywordHolder("outreach", 2)
        ),

        PURCHASING(
                new KeywordHolder("purchasing", 10),
                new KeywordHolder("equipment", 5), new KeywordHolder("order", 5),
                new KeywordHolder("request", 2), new KeywordHolder("new", 2), new KeywordHolder("buy", 2)
        ),

        CAREER_SERVICES(
                new KeywordHolder("handshake", 10),
                new KeywordHolder("career", 5), new KeywordHolder("jobposting", 5),
                new KeywordHolder("job", 2), new KeywordHolder("posting", 2), new KeywordHolder("services", 2)
        ),

        ISS(
                new KeywordHolder("terradota", 10), new KeywordHolder("glacier", 10),
                new KeywordHolder("international", 5), new KeywordHolder("taxpreparation", 5),
                new KeywordHolder("student", 2), new KeywordHolder("tax", 2), new KeywordHolder("form", 2)
        ),

        HUMAN_REVIEW(),
        FAULTY_TRASH();

        private KeywordHolder[] keywords;

        Team (KeywordHolder...keywords){

                this.keywords = keywords;
        }

        public KeywordHolder[] getKeywords () {

                return keywords;
        }


}


