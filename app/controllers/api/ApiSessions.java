package controllers.api;

import com.google.gson.JsonSerializer;
import models.ConferenceEvent;
import models.LightningTalk;
import models.Talk;

import java.util.List;

public class ApiSessions extends JsonpController {

    public static void talks(boolean details) {
        List<Talk> talks = Talk.findAllValidatedOn(ConferenceEvent.CURRENT);
        renderJSON(talks, getSerializers(details));
    }

    public static void talk(long id, boolean details) {
        Talk talk = Talk.findById(id);
        notFoundIfNull(talk);
        renderJSON(talk, getSerializers(details));
    }

    public static void lightningTalks(boolean details) {
        List<LightningTalk> talks = LightningTalk.findAllOn(ConferenceEvent.CURRENT);
        renderJSON(talks, getSerializers(details));
    }

    public static void lightningTalk(long id, boolean details) {
        LightningTalk talk = LightningTalk.findById(id);
        notFoundIfNull(talk);
        renderJSON(talk, getSerializers(details));
    }

    private static JsonSerializer[] getSerializers(boolean details) {
        return details ? DETAILED_TALK_SERIALIZERS : TALK_SERIALIZERS;
    }
    private static JsonSerializer TALK_SERIALIZERS[] = new JsonSerializer[] {
            new TalkJsonSerializer(false), new LightningTalkJsonSerializer(false), new MemberJsonSerializer()
    };

    private static JsonSerializer DETAILED_TALK_SERIALIZERS[] = new JsonSerializer[] {
            new TalkJsonSerializer(true), new LightningTalkJsonSerializer(true), new MemberJsonSerializer()
    };
}